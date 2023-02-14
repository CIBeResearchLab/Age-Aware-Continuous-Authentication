import os
import subprocess
import pandas as pd
from tqdm import tqdm 
import shutil
from icecream import ic
from sys import exit as e


if __name__ == "__main__":
  root_dir = "/mnt/ca_data/Project Resources - USF/Participant Data/"
  dest_phys_dir = "/data/dataset/ca_data/physiology"
  dest_tag_dir = "/data/dataset/ca_data/timestamp"

  pbar = tqdm(os.listdir(root_dir))
  for sub in pbar:
    sub_dir = os.path.join(root_dir, sub)
    if not os.path.isdir(sub_dir):
      continue
    
    dest_sub_phys_dir = os.path.join(dest_phys_dir, sub)
    dest_sub_tag_dir = os.path.join(dest_tag_dir, sub)
    if not os.path.isdir(dest_sub_phys_dir):
      os.mkdir(dest_sub_phys_dir)
    if not os.path.isdir(dest_sub_tag_dir):
      os.mkdir(dest_sub_tag_dir)
    
    for session in os.listdir(sub_dir):
      session_dir = os.path.join(sub_dir, session, "physiology")
      if not os.path.isdir(session_dir):
        continue
      
      dest_phys_session_dir = os.path.join(dest_sub_phys_dir, session)
      dest_tag_session_dir = os.path.join(dest_sub_tag_dir, session)
      if not os.path.isdir(dest_phys_session_dir):
        os.mkdir(dest_phys_session_dir)
      if not os.path.isdir(dest_tag_session_dir):
        os.mkdir(dest_tag_session_dir)
      for phys in os.listdir(session_dir):
        phys_path = os.path.join(session_dir, phys)
        if os.path.splitext(phys_path)[-1] not in [".txt", ".csv"]:
          continue
        if phys == "tags.csv":
          shutil.copy(phys_path, dest_tag_session_dir)
        else:
          shutil.copy(phys_path, dest_phys_session_dir)
