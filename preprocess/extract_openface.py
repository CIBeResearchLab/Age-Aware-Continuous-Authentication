
import subprocess
import os
import shutil
from tqdm import tqdm
from pathlib import Path
from sys import exit as e
from icecream import ic 
from tqdm import tqdm

FNULL = open(os.devnull, 'w')
def sh(cmd):
  return subprocess.call(cmd, stdout=FNULL, stderr=subprocess.STDOUT)


def ex_openface(openface_path, context_path):
  args = (openface_path, "-f", context_path)
  stat = sh(args)
  return stat

def make_directories(path):
  if not os.path.isdir(path):
    os.mkdir(path)


if __name__ == "__main__":
  # Input and binaries
  root_dir = "/data/dataset/ca_data/affect_split_videos/"

  # Destination
  dest_root = "/data/dataset/ca_data/openface"
  # dest_root = "./processed/"
  dest_csv_root = os.path.join(dest_root, "csv")
  dest_aligned_root = os.path.join(dest_root, "aligned")
  dest_vid_root = os.path.join(dest_root, "viz")

  # Openface
  openface_dir = "/home/saandeepaath/Desktop/OpenFace/build"
  binaries = "bin/FeatureExtraction"
  openface_path = os.path.join(openface_dir, binaries)
  # output_path = os.path.join(openface_dir, "processed")
  output_path = "./processed"

  pbar = tqdm(os.listdir(root_dir))
  for subject in pbar:
    subject_dir = os.path.join(root_dir, subject)

    # Define root destination directories
    dest_csv_subject = os.path.join(dest_csv_root, subject)
    dest_aligned_subject = os.path.join(dest_aligned_root, subject)
    dest_vid_subject = os.path.join(dest_vid_root, subject)

    # Create directories
    make_directories(dest_csv_subject)
    make_directories(dest_aligned_subject)
    make_directories(dest_vid_subject)

    for sess in os.listdir(subject_dir):
      sess_dir = os.path.join(subject_dir, sess)
      if not os.path.isdir(sess_dir):
        continue

       # Define root destination directories
      dest_csv_session = os.path.join(dest_csv_subject, sess)
      dest_aligned_session = os.path.join(dest_aligned_subject, sess)
      dest_vid_session = os.path.join(dest_vid_subject, sess)

      # Create directories
      make_directories(dest_csv_session)
      make_directories(dest_aligned_session)
      make_directories(dest_vid_session)

      for vid in os.listdir(sess_dir):
        vid_path = os.path.join(sess_dir, vid)
        if os.path.splitext(vid_path)[-1] != ".mp4":
          continue
        vid_name = os.path.splitext(vid)[0]

        # Defin source paths
        src_csv = os.path.join(output_path, f"{vid_name}.csv")
        src_aligned = os.path.join(output_path, f"{vid_name}_aligned")
        src_vid = os.path.join(output_path, f"{vid_name}.avi")

        # Define destination paths
        dest_csv = os.path.join(dest_csv_session, f"{vid_name}.csv")
        dest_aligned = os.path.join(dest_aligned_session, vid_name)
        dest_vid = os.path.join(dest_vid_session, f"{vid_name}.avi")
        
        # Create subdirectory for aligned images
        make_directories(dest_aligned)

        pbar.set_description(vid_path)
        # print("Extracting openface features")
        status = ex_openface(openface_path, vid_path)
        
        if not os.path.isdir(output_path):
        # if status != 0:
          print(f"{subject}, {context} failed to extract openface features")
          continue

        # Copy data
        print("Copying data ...")
        shutil.copy(src_csv, dest_csv)
        if os.path.exists(dest_aligned):
          shutil.rmtree(dest_aligned)
          shutil.copytree(src_aligned, dest_aligned)
        shutil.copy(src_vid, dest_vid)
        # print("Removing `processed` directory...")
        shutil.rmtree(output_path)
      




  