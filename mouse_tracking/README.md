# **Code to generate mouse movement data**

## **1. Setup**
```
pip install -r requirements.txt
```

## **2. Generate raw data**
```
python playground.py --fname <subject_name> --dest <output_dir>
python playground.py --fname saandeep --dest ./logs/
```

  * This will generate a raw data file called `raw.csv` in the `output_dir` path.

## **3. Generate stats**
```
python generate_stats.py --dest <subject_dir_path>
python generate_stats.py --dest ./logs/saandeep
```

  * This will generate a stat file called `processed.csv` in the `subject_dir_path`. 