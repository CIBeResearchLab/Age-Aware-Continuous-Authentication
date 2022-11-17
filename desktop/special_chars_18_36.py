import os
import docx
import re
from genericpath import exists
import matplotlib.pyplot as plt
from desktop.account_format import AC
from pre_proc_data import PreProcData
from participant_metadata import A18_36


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
        if self.info[0] in A18_36:
            if exists(self.file):
                with open(self.file, 'r') as f:
                    document = docx.Document(self.file)
                    for line in document.paragraphs:
                        if line.text:
                            w = r'[^\s]+\suser\s(.*?)\spass\s(.*?)\s'
                            k = re.findall(w, str(line.text))
                            # print(f"{[i[PWD] for i in k]}{self.info}")
                            for vl in [e.value for e in AC]:
                                sl = list(k[vl][PWD])
                                regex = re.compile(r'\w')
                                filtered = [
                                    i for i in sl if not regex.match(i)]
                                freq = dict((i, sl.count(i))
                                            for i in set(filtered))

                                d1.update({key: freq.get(key, 0) + d1.get(key, 0)
                                           for key in set(list(freq.keys()) + list(d1.keys()))})
        self.returnInfo = x

    x1.custom(j, l1)
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
        'Occurences of Special Characters in Passwords for Ages 18-36 on Desktop')
    plt.show()
