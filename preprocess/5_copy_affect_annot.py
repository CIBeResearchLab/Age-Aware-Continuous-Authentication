import os
import subprocess
import pandas as pd
from tqdm import tqdm 
import shutil
from datetime import datetime
import difflib
from icecream import ic
from sys import exit as e


if __name__ == "__main__":
  root_dir = "/mnt/ca_data/"
  dest_annot_dir = "/data/dataset/ca_data/affect_annot"
  months_dict = {"January": 1, "February": 2, "March": 3, "April": 4, "May": 5, "June": 6, \
    "July": 7, "August": 8, "September": 9, "October": 10, "Nov": 11, "December": 12}

  completed_data = []

  # MANUALLY IDENTIFIED DUMMY ANNOTATIONS. ADD MORE AS YOU GO
  ignore_annot = {
    "P0014": {"May_23_2022": "user_data_2022.05.23.11.11.43.5130.txt"}, 
    "P0010": {"May_24_2022": "user_data_2022.05.24.17.59.00.2000.txt", "May_12_2022": "user_data_2022.05.12.15.38.09.3700.txt"}, 
    "P0015": {"July_20_2022": "user_data_2022.07.20.12.10.01.0560.txt", "May_17_2022": "user_data_2022.05.17.16.39.52.2480.txt"},
    "P0029": {"Nov_14_2022": "user_data_2022.11.14.09.55.32.6540.txt"},
  }

  pbar = tqdm(os.listdir(root_dir))
  for sub in pbar:
    sub_dir = os.path.join(root_dir, sub)
    if not os.path.isdir(sub_dir):
      continue
    dest_sub_annot_dir = os.path.join(dest_annot_dir, sub)
    if not os.path.isdir(dest_sub_annot_dir):
      os.mkdir(dest_sub_annot_dir)
    
    sessions = os.listdir(sub_dir)
    session_date_lst = []
    session_date_dict = {}
    session_date_order = {}
    for sess in sessions:
      if "2022" not in sess and "2023" not in sess:
        continue 
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
      session_dir = os.path.join(sub_dir, session, "videos_annotation")
      if not os.path.isdir(session_dir):
        continue
      
      # Add session # while creating destination directory
      order = session_date_order[session]
      dest_session_dir = os.path.join(dest_sub_annot_dir, f"S{order+1}_{session}")
      if not os.path.isdir(dest_session_dir):
        os.mkdir(dest_session_dir)

      ic(sub, session)
      files = [file for file in os.listdir(session_dir) if (file.lower().endswith('.txt') and "user" in file)]
      files.sort(key = lambda x: datetime.strptime(x.split("_")[-1][:-4], '%Y.%m.%d.%H.%M.%S.%f'))

      if len(files) > 4:
        if sub in ignore_annot:
          if session in ignore_annot[sub]:
            del_fl = ignore_annot[sub][session]
            files.remove(del_fl)
        
        # IMPORTANT TO IDENTIFY DUMMY ANNOTATIONS
        # timestamps = [datetime.strptime(x.split("_")[-1][:-4], '%Y.%m.%d.%H.%M.%S.%f') for x in files]
        # diff = [(t - s).seconds for s, t in zip(timestamps, timestamps[1:])]
        # ind = [i+1 for i in range(len(diff)) if diff[i] < 60]

      annot_cnt = 1
      for fl in files:
        fl_path = os.path.join(session_dir, fl)
        if os.path.splitext(fl_path)[-1] != ".txt":
          continue
        dest_fl_path = os.path.join(dest_session_dir, f"annot_v{annot_cnt}.txt")
        shutil.copy(fl_path, dest_fl_path)
        annot_cnt += 1


