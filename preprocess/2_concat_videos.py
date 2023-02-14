import os
import subprocess
import pandas as pd
from tqdm import tqdm 
from icecream import ic
from sys import exit as e
from datetime import datetime


def build_command(txt_path, dest_split_path):
  ffmpeg = "/usr/bin/ffmpeg"
  # ffmpeg = "ffmpeg"
  commandList = [
    ffmpeg, 
    "-f",
    "concat",
    "-safe",
    "0",
    "-i", 
    txt_path, 
    "-c",
    "copy",
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
  vid_dir = "/data/dataset/ca_data/compressed_video"
  dest_dir = "/data/dataset/ca_data/merged_compressed_video"

  pbar = tqdm(sorted(os.listdir(vid_dir)))
  for sub in pbar:
    sub_dir = os.path.join(vid_dir, sub)
    if not os.path.isdir(sub_dir):
      continue
    
    dest_sub_dir = os.path.join(dest_dir, sub)
    if not os.path.isdir(dest_sub_dir):
      os.mkdir(dest_sub_dir)
    
    for session in os.listdir(sub_dir):
      session_dir = os.path.join(sub_dir, session)
      
      if not os.path.isdir(session_dir):
        continue
      dest_session_dir = os.path.join(dest_sub_dir, session)
      if not os.path.isdir(dest_session_dir):
        os.mkdir(dest_session_dir)
      
      for vid_type in os.listdir(session_dir):
        vid_type_dir = os.path.join(session_dir, vid_type)
        if not os.path.isdir(vid_type_dir):
          continue
        
        dest_vid_path = os.path.join(dest_session_dir, f"{vid_type}.mp4")
        dest_text_path = os.path.join(dest_session_dir, f"{vid_type}.txt")

        lines = []
        for vid in sorted(os.listdir(vid_type_dir)):
          vid_path = os.path.join(vid_type_dir, vid)
          if os.path.splitext(vid_path)[-1] != ".mp4":
            continue
          lines.append(f"file '{vid_path}'")
        
        with open(dest_text_path, 'w') as f:
          for line in lines:
            f.write(line)
            f.write('\n')
        cmd = build_command(dest_text_path, dest_vid_path)
        stat = runFFmpeg(cmd)
        if stat == 0:
          print(f"Could not run {vid_path} video")
        print(vid_path, "done!")
        print("-"*40)
      
