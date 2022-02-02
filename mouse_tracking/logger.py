import os
import csv
from datetime import datetime

class Logger:
  def __init__(self, root_path, user_id):
    self.root_path = root_path
    self.user_id = user_id
    self.moves = []
    self.clicks = []
    self.headers = ["timestamp", "x", "y", "state", "time_stamp"]
    self.drag = False

  def log_moves(self, x, y, state, time_stamp):
    print(x, y)
    if self.drag == True:
      state = "Drag"
    self.moves.append([datetime.now().time(), x, y, state, time_stamp])

  def save(self):
    print("Save")
    with open(os.path.join(self.root_path, f"{self.user_id }.csv"), "w") as f:
      writer = csv.writer(f)
      writer.writerow(self.headers)
      writer.writerows(self.moves)
