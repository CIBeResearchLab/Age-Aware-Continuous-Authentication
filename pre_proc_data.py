import os
import re


class PreProcData:
    dir_list = []

    def listdirs(self, dir):
        for file in os.listdir(dir):
            d = os.path.join(dir, file)
            if os.path.isdir(d):
                self.dir_list.append(d)
                self.listdirs(d)

    def filterlist(self):
        p = re.compile(f'.*?{self.path}$')
        l2 = [s for s in self.dir_list if p.match(s)]
        self.dir_list = l2

    def __init__(self, path, cwd) -> None:
        # Path of the task folder -> String
        self.path = path
        # TODO: Determine if these need to be moved outside and called from the main file
        self.listdirs(cwd)
        self.filterlist()

    def parse(self, filepath):
        regex = r"(?P<participant>P\d+)\/(?P<date>[^\/]+)"
        matches = re.search(regex, filepath)
        return (matches.group(1), matches.group(2))

    def custom(self, func, l):
        self.dir_list = list(set(self.dir_list))
        for i in self.dir_list:
            self.file = f'{i}/{l}'
            self.info = self.parse(self.file)
            func(self)
            # ap[self.info[0]][self.info[1]] = self.returnInfo
