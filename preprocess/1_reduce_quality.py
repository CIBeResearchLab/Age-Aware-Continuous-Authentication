import os
import subprocess
import pandas as pd
from tqdm import tqdm 
from icecream import ic
from sys import exit as e



def build_command(vid_path, dest_split_path):
  ffmpeg = "/usr/bin/ffmpeg"
  # ffmpeg = "ffmpeg"
  commandList = [
    ffmpeg, 
    "-hide_banner", 
    "-loglevel",
    "error",
    "-i", 
    vid_path, 
    "-vf",
    "scale=640:480",
    "-y",
    dest_split_path
  ]
  return commandList

def runFFmpeg(commands):  
  print("running ffmpeg")  
  if subprocess.run(commands).returncode == 0:
    return 1
  else:
    return 0

if __name__ == "__main__":
  vid_dir = "/mnt/ca_data/Project Resources - USF/Participant Data/"
  # vid_dir = "/data/dataset/shed/ca_data/"
  dest_dir = "/data/dataset/ca_data/compressed_video"

  pbar = tqdm(os.listdir(vid_dir))
  for sub in pbar:
    sub_dir = os.path.join(vid_dir, sub)
    if not os.path.isdir(sub_dir):
      continue
    
    dest_sub_dir = os.path.join(dest_dir, sub)
    if not os.path.isdir(dest_sub_dir):
      os.mkdir(dest_sub_dir)
    
    for session in os.listdir(sub_dir):
      session_dir = os.path.join(sub_dir, session, "videos")
      if not os.path.isdir(session_dir):
        continue
      
      dest_session_dir = os.path.join(dest_sub_dir, session)
      if not os.path.isdir(dest_session_dir):
        os.mkdir(dest_session_dir)
      for video_type in os.listdir(session_dir):
        vid_type_path = os.path.join(session_dir, video_type)
        if not os.path.isdir(vid_type_path):
          continue
        
        dest_vid_type_dir = os.path.join(dest_session_dir, video_type)
        if not os.path.isdir(dest_vid_type_dir):
          os.mkdir(dest_vid_type_dir)
        for video in os.listdir(vid_type_path):
          vid_path = os.path.join(vid_type_path, video)
          if os.path.splitext(vid_path)[-1] != ".mp4":
            continue
          
          vid_name = os.path.splitext(video)[0] + "_compressed.mp4"
          dest_vid_path = os.path.join(dest_vid_type_dir, vid_name)
          pbar.set_description(f"{sub}|{session}|{video_type}|{video}")
          cmd = build_command(vid_path, dest_vid_path)
          stat = runFFmpeg(cmd)
          if stat == 0:
            print(f"Could not run {sub} video")