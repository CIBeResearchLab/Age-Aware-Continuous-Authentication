
import os
import subprocess
import pandas as pd
from tqdm import tqdm 
import difflib
from datetime import datetime
from icecream import ic
from sys import exit as e

s2 = [40, 149, 251, 358]
s3 = [40, 140, 240, 328]
def build_command(vid_path, dest_split_path):
  # ffmpeg = "/usr/bin/ffmpeg"
  ffmpeg = "ffmpeg"
  commandList = [
    ffmpeg, 
    # "-ss", 
    # "328",
    # "-t", 
    # "10", 
    "-i",
    vid_path,
    "-f",
    "wav",
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
  # cmd = build_command("./sample_data/S3_Data Collection Video.mp4","./sample_data/videos/S3_v4.wav")
  # stat = runFFmpeg(cmd)
  # e()
  months_dict = {"January": 1, "February": 2, "March": 3, "April": 4, "May": 5, "June": 6, \
    "July": 7, "August": 8, "September": 9, "October": 10, "November": 11, "December": 12}

  vid_dir = "/data/dataset/ca_data/merged_compressed_video"
  dest_dir = "/data/dataset/ca_data/merged_compressed_audio"

  pbar = tqdm(sorted(os.listdir(vid_dir)))
  for sub in pbar:
    sub_dir = os.path.join(vid_dir, sub)
    if not os.path.isdir(sub_dir):
      continue
    
    dest_sub_dir = os.path.join(dest_dir, sub)
    if not os.path.isdir(dest_sub_dir):
      os.mkdir(dest_sub_dir)
    
    # Convert session names to datetime
    sessions = os.listdir(sub_dir)
    session_date_lst = []
    session_date_dict = {}
    session_date_order = {}
    for sess in sessions:
      dt = sess.split("_")
      month = dt[0]
      day = dt[1]
      year = dt[2]

      orig_month = difflib.get_close_matches(month, months_dict.keys())[0]
      session_date_lst.append(f"{year}-{months_dict[orig_month]}-{day}")
      session_date_dict[session_date_lst[-1]] = sess
    
    # Find session number
    session_date_lst.sort(key=lambda date: datetime.strptime(date, "%Y-%m-%d"))
    for i in range(len(session_date_lst)):
      act_session = session_date_dict[session_date_lst[i]]
      session_date_order[act_session] = i

    for session in os.listdir(sub_dir):
      session_dir = os.path.join(sub_dir, session)
      if not os.path.isdir(session_dir):
        continue
      
      # Add session # while creating destination directory
      order = session_date_order[session]
      dest_session_dir = os.path.join(dest_sub_dir, f"S{order+1}_{session}")
      if not os.path.isdir(dest_session_dir):
        os.mkdir(dest_session_dir)
      
      for vid in os.listdir(session_dir):
        vid_path = os.path.join(session_dir, vid)
        if os.path.splitext(vid_path)[-1] != ".mp4":
          continue
        vid_name = os.path.splitext(vid_path)[0].split("/")[-1]
        dest_path = os.path.join(dest_session_dir, f"{vid_name}.wav")
        # ic(vid_path, dest_path)
        # continue
        cmd = build_command(vid_path,dest_path)
        stat = runFFmpeg(cmd)
        if stat == 0:
          print(f"Could not run {vid_path} video")