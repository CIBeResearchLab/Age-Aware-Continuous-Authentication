import os
import subprocess
import pandas as pd
from tqdm import tqdm 
import difflib
import json
from datetime import datetime
from icecream import ic
from sys import exit as e

def build_command(vid_path, start, end, dest_split_path):
  ffmpeg = "/usr/bin/ffmpeg"
  # ffmpeg = "ffmpeg"
  commandList = [
    ffmpeg, 
    "-hide_banner", 
    "-loglevel",
    "error",
    "-ss", 
    start, 
    "-to", 
    end, 
    "-i", 
    vid_path, 
    "-c",
    "copy",
    "-y",
    dest_split_path
  ]
  return commandList

def runFFmpeg(commands):  
  if subprocess.run(commands).returncode == 0:
    return 1
  else:
    return 0

if __name__ == "__main__":
  vid_dir = "/data/dataset/ca_data/merged_compressed_video"
  dest_dir = "/data/dataset/ca_data/affect_split_videos"
  annot_dir = "/data/dataset/ca_data/affect_annot"
  timestamp_file = "./sample_data/timestamps_v1.json"

  months_dict = {"January": 1, "February": 2, "March": 3, "April": 4, "May": 5, "June": 6, \
  "July": 7, "August": 8, "September": 9, "October": 10, "November": 11, "December": 12}

  with open(timestamp_file, 'r') as fp:
    timestamps = json.load(fp)

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
        if vid == "desktop_view.mp4":
          continue
        if os.path.splitext(vid_path)[-1] != ".mp4":
          continue
        vid_name = vid.split(".")[0]
        for k in range(4):
          pbar.set_description(f"{sub}, S{order+1}, {vid_name}, {k+1}")
          dest_vid_path = os.path.join(dest_session_dir, f"{vid_name}_v{k+1}.mp4")
          try:
            vid_ts = timestamps[sub][f"S{order+1}"][vid_name][f"vid{k+1}"]
          except KeyError as e:
            ic(f"{sub}, S{order+1}, {vid_name}, {k+1} failed!!")
          start = vid_ts[0]
          end = vid_ts[1]
          cmd = build_command(vid_path, start, end, dest_vid_path)
          stat = runFFmpeg(cmd)
          if stat == 0:
            print(f"Could not run for {dest_vid_path}")