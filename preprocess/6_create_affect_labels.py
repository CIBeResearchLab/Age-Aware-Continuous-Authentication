import os
import subprocess
import pandas as pd
from tqdm import tqdm 
import shutil
from datetime import datetime
import difflib
import csv
from icecream import ic
from sys import exit as e


if __name__ == "__main__":
  affect_dir = "/data/dataset/ca_data/affect_split_videos"
  annot_dir = "/data/dataset/ca_data/affect_annot"

  annot_dict = {}

  valence_dict = {"positive": 1, "negative": -1, "same": 0}
  expression_dict = {"neutral": 0, "angry": 1, "happy": 2, "sad": 3, "fear": 4, "disgust": 5}

  pbar = tqdm(os.listdir(annot_dir))
  for sub in pbar:
    sub_dir = os.path.join(annot_dir, sub)
    if not os.path.isdir(sub_dir):
      continue
    
    annot_dict[sub] = {}
    for sess in os.listdir(sub_dir):
      sess_dir = os.path.join(sub_dir, sess)
      if not os.path.isdir(sess_dir):
        continue
      sess_num = sess.split("_")[0]
      annot_dict[sub][sess_num] = {}

      for annot in os.listdir(sess_dir):
        annot_file = os.path.join(sess_dir, annot)
        ic(annot_file)
        if os.path.splitext(annot_file)[-1] != ".txt":
          continue
        
        labels = []
        text_file =  open(annot_file, 'r')
        labels = text_file.read().splitlines()
        labels = list(filter(None, labels))
        
        valence = valence_dict[labels[-1]]
        arousal = labels[-2]
        expression = labels[:-2]

        ic(valence, arousal, expression)
        e()
  
  ic(annot_dict)
  e()
