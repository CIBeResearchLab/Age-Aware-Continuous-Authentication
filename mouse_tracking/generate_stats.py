import os
import math
import numpy as np
import pandas as pd
import pyautogui
import argparse
import matplotlib.pyplot as plt
from sys import exit as e

def dot(vA, vB):
  return vA[0]*vB[0]+vA[1]*vB[1]

def generate_ang(lineA, lineB):
  vA = [(lineA[0][0]-lineA[1][0]), (lineA[0][1]-lineA[1][1])]
  vB = [(lineB[0][0]-lineB[1][0]), (lineB[0][1]-lineB[1][1])]
  dot_prod = dot(vA, vB)
  magA = dot(vA, vA)**0.5
  magB = dot(vB, vB)**0.5
  cos_ = dot_prod/magA/magB
  angle = math.acos(dot_prod/magB/magA)
  ang_deg = math.degrees(angle)%360
  if ang_deg-180>=0:
    return 360 - ang_deg
  else: 
    return ang_deg
  return ang_deg


def generate_data(df, eps, opt):
  lineA = np.array([[0, y], [x, y]])
  
  timestamp = df.loc[df['state']=="Move"]['time_stamp'].to_numpy()
  diff_time = np.diff(timestamp)

  intervals = list(np.argwhere(diff_time > eps).ravel())

  clicked = df.index[df['state'].between("Pressed", "Released")].tolist()
  diff_clicked = list(zip(clicked, clicked[1:]))[::2]
  diff_clicked = [i[1] - i[0] for i in diff_clicked]
  diff_clicked = [idx*2 for idx, element in enumerate(diff_clicked) if element > 1] 
  drag_interval = [(clicked[i], clicked[i+1]) for i in diff_clicked]

  new_df = {'Action':[], 'Distance':[], "Time":[], "Direction":[]}
  prev_int = 0
  for next_int in intervals:
    segment = df.loc[prev_int:next_int, ['x', 'y']].to_numpy()
    columns = df.loc[prev_int:next_int, 'state']
    time_step = df.loc[prev_int:next_int, 'time_stamp'].to_numpy()
    #Time
    time = time_step[-1] - time_step[0]
    new_df['Time'].append(time)
    #action
    action = columns.values.tolist()[0]
    new_df['Action'].append(action)

    prev_int = next_int+1
    if columns.values.tolist()[0] in valid_moves and columns.all():
      p1 = np.array([segment[0][0], segment[0][1]])
      p2 = np.array([segment[-1][0], segment[-1][1]])
      #Distance
      distance = np.sqrt(np.sum(np.square(p1 - p2)))
      new_df["Distance"].append(distance)
      lineB = np.array([p1, p2])
      #Angle
      ang_deg = generate_ang(lineA, lineB)
      new_df["Direction"].append(ang_deg)
    
    else:
      new_df["Distance"].append(-1)
      new_df["Direction"].append(-1)
    prev_int = next_int+1
  new_df = pd.DataFrame(new_df)
  new_df.to_csv(os.path.join(opt.dest, "processed.csv"))

def plot_line(df, dest_path):
  arr = df.loc[:, ['x', 'y']].to_numpy()
  arr[:, 1] = y - arr[:, 1]
  plt.plot(arr[:, 0], arr[:, 1])
  plt.savefig(dest_path)
  plt.close()

if __name__ == "__main__":
  parser = argparse.ArgumentParser()
  parser.add_argument("--dest", type=str, help="output directory")
  opt = parser.parse_args()

  x = pyautogui.size()[0]
  y = pyautogui.size()[1]

  valid_moves = ['Move', "Drag", "Pressed"]

  #Replace with arguments
  fname = os.path.join(opt.dest, "raw.csv")
  df = pd.read_csv(fname)
  
  plot_line(df, os.path.join(opt.dest, "line_plot.png"))
  generate_data(df, 0.7, opt)