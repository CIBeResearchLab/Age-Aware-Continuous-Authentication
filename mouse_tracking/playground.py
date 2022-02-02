#!/usr/bin/env /Users/saandeep/Projects/bio_auth/mouse_tracking/.venv/bin/python3
from pynput import mouse
# from logger import Logger
import pyautogui
import time
import os
from pathlib import Path
import argparse

import csv
from datetime import datetime
from sys import exit as e




class Logger:
  def __init__(self, root_path, user_id):
    self.root_path = root_path
    self.user_id = user_id
    self.moves = []
    self.clicks = []
    self.headers = ["timestamp", "x", "y", "state", "time_stamp"]
    self.drag = False

  def log_moves(self, x, y, state, time_stamp):
    if self.drag == True:
      state = "Drag"
    self.moves.append([datetime.now(), x, y, state, time_stamp])

  def save(self):
    with open(os.path.join(self.root_path, f"{self.user_id }.csv"), "w") as f:
      writer = csv.writer(f)
      writer.writerow(self.headers)
      writer.writerows(self.moves)

def on_move(x, y):
  logger.log_moves(round(x, 2), round(y, 2), "Move", time.time() - start_time)

def on_click(x, y, button, pressed):
  if pressed:
    logger.log_moves(round(x, 2), round(y, 2), "Pressed", time.time() - start_time)
    logger.drag = True
  if not pressed:
    logger.drag = False
    logger.log_moves(round(x, 2), round(y, 2), "Released", time.time() - start_time)
    logger.save()

def on_scroll(x, y, dx, dy):
    logger.log_moves(round(x, 2), round(y, 2), "Scroll", time.time() - start_time)


if __name__ == "__main__":
  parser = argparse.ArgumentParser()
  parser.add_argument("--fname", type=str, help="Name of the csv file")
  parser.add_argument("--dest", type=str, help="output directory")
  opt = parser.parse_args()

  print(f"Screen size: {pyautogui.size()}")
  start_time = time.time()

  
  # dest_path = Path("/Users/saandeep/Projects/bio_auth/mouse_track_batch")

  dest_dir = os.path.join(opt.dest, opt.fname)
  # dest_dir = os.path.join(dest_path, "Physiology")
  if not os.path.isdir(dest_dir):
    os.mkdir(dest_dir)

  print(dest_dir)
  logger = Logger(dest_dir, "raw")
  listener = mouse.Listener(
      on_move=on_move,
      on_click=on_click,
      on_scroll=on_scroll)

  listener.start()
  listener.join()

  # headers = ["a", "b", "c"]
  # moves = [[1, 2, 3], [4, 5, 6], [6, 7, 8]]
  # with open(os.path.join(dest_dir, "sample.csv"), "w") as f:
  #     writer = csv.writer(f)
  #     writer.writerow(headers)
  #     writer.writerows(moves)

