import os
import re
from genericpath import exists
from enum import IntEnum
import matplotlib.pyplot as plt
from pre_proc_data import PreProcData


def main():
    path = '../Participant_Data'
    os.chdir(path)
    cwd = os.getcwd()
    x1 = PreProcData('CA_output_phone', cwd)
    l1 = 'ca_home_act_2.txt'
    d1 = {}

    def j(self):
        x = []
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

                for sl in lines:
                    # print(f'{lines}{self.info}')
                    regex = re.compile(r'\w')
                    filtered = [i for i in sl if not regex.match(i)]
                    freq = dict((i, sl.count(i)) for i in set(filtered))
                    # print(freq)
                    c = {}
                    d1.update({key: freq.get(key, 0) + d1.get(key, 0)
                              for key in set(list(freq.keys()) + list(d1.keys()))})
                    # for key in freq:
                    #   # print(key)
                    #   if key in d1:
                    #     d1[key] = freq[key] + d1[key]
                    #   else:
                    #     d1.update(freq)
                    # print(d1)

        self.returnInfo = x

    x1.custom(j, l1)
    # del d1[' ']

    # print(d1)

    new_names = []
    new_values = []
    for w in sorted(d1, key=d1.get, reverse=True):
        new_names.append(w)
        new_values.append(d1[w])

    fig, ax = plt.subplots()
    ax.bar(new_names, new_values)
    for i, value in enumerate(new_values):
        ax.text(i-(0.3 if value > 9 else 0.2), value+3, str(value))
        xmin, xmax = ax.get_ylim()
        ax.set_ylim(xmin, 1.01*xmax)
    plt.ylabel("# of occurences")
    plt.xlabel("Special Characters")
    plt.title(
        'Occurences of Special Characters in Passwords for All Ages on Mobile Phone')
    plt.show()
