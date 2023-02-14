import os 
from icecream import ic
from sys import exit as e

if __name__ == "__main__":
  root_dir = "/data/dataset/ca_data/physiology"

  for sub in os.listdir(root_dir):
    sub_dir = os.path.join(root_dir, sub)
    if not os.path.isdir(sub_dir):
      continue
    for sess in os.listdir(sub_dir):
      sess_dir = os.path.join(sub_dir, sess)
      if not os.path.isdir(sess_dir):
        continue
  