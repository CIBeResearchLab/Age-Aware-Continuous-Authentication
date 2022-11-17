from collections import Counter
import os
import re
from genericpath import exists
import matplotlib.pyplot as plt
from desktop.account_format import AC
from keyboard_info import findZone
from participant_metadata import A18_36
from pre_proc_data import PreProcData


def main():
    path = '../Participant_Data'
    os.chdir(path)
    cwd = os.getcwd()

    # Account Context
    x1 = PreProcData('CA_output_phone', cwd)
    l1 = 'ca_home_act_2.txt'
    d1 = {}

    def j(self):
        x = []
        if self.info[0] in A18_36:
            if exists(self.file):
                with open(self.file, 'r') as f:
                    lines = f.readlines()
                    lines = [i.strip() for i in lines]
                    if (lines[0] == 'personal_user'):
                        lines = [lines[f+3]
                                 for f in range(len(lines)) if f % 4 == 0]

                    else:
                        lines = [lines[f+1]
                                 for f in range(len(lines)) if f % 2 == 0]

                    # print(f'{lines}{self.info}')
                    if not (self.info[0] in list(d1.keys())):
                        d1[self.info[0]] = {}
                    d1[self.info[0]][self.info[1]] = {}
                    for cnt, pwd in enumerate(lines):
                        for match in re.finditer(r'\W', pwd):
                            startIndex = match.start()
                            if (not startIndex == 0):
                                prvChar = pwd[startIndex-1]
                                spclChar = match.group()
                                zone = findZone(spclChar, prvChar)
                                d1[self.info[0]][self.info[1]][f"{cnt}"] = {
                                    "Special Character": spclChar, "Previous Character": prvChar, "Zone": zone}

        self.returnInfo = x

    x1.custom(j, l1)
    # print(d1)
    allZones = []
    for k, v in d1.items():
        for v, b in d1[k].items():
            for n, m in d1[k][v].items():
                allZones.append(d1[k][v][n]["Zone"])
    print(Counter(allZones))
