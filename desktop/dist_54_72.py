from collections import Counter
import os
import re
from genericpath import exists
import matplotlib.pyplot as plt
from desktop.account_format import AC
from keyboard_info import findDist
from participant_metadata import A54_72
from pre_proc_data import PreProcData
import docx


def main():
    path = '../Participant_Data'
    os.chdir(path)
    cwd = os.getcwd()
    PWD = 1
    x1 = PreProcData('CA_output_desktop', cwd)
    l1 = 'task_free_form_pass.docx'
    d1 = {}

    def j(self):
        x = []
        if self.info[0] in A54_72:
            if exists(self.file):
                with open(self.file, 'r') as f:
                    document = docx.Document(self.file)
                    for line in document.paragraphs:
                        if line.text:
                            w = r'[^\s]+\suser\s(.*?)\spass\s(.*?)\s'
                            k = re.findall(w, str(line.text))
                            print(f"{[i[PWD] for i in k]}{self.info}")
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
                                        dist = findDist(spclChar, prvChar)
                                        print(
                                            f'{pwd} | {prvChar} to {spclChar}: {abs(dist)}')

        self.returnInfo = x

    x1.custom(j, l1)
