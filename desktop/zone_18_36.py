from collections import Counter
import os
import re
from genericpath import exists
import matplotlib.pyplot as plt
from desktop.account_format import AC
from keyboard_info import findZone
from participant_metadata import A18_36
from pre_proc_data import PreProcData
import docx


def main():
    path = '../Participant_Data'
    os.chdir(path)
    cwd = os.getcwd()
    x1 = PreProcData('CA_output_desktop', cwd)
    l1 = 'task_free_form_pass.docx'
    d1 = {}
    PWD = 1

    def j(self):
        x = []
        if self.info[0] in A18_36:
            if exists(self.file):
                with open(self.file, 'r') as f:
                    document = docx.Document(self.file)
                    for line in document.paragraphs:
                        if line.text:
                            w = r'[^\s]+\suser\s(.*?)\spass\s(.*?)\s'
                            k = re.findall(w, str(line.text))
                            lines = [i[PWD] for i in k]
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
    x1.custom(j, l1)
    # print(d1)
    allZones = []
    for k, v in d1.items():
        for v, b in d1[k].items():
            for n, m in d1[k][v].items():
                allZones.append(d1[k][v][n]["Zone"])
    print(Counter(allZones))
