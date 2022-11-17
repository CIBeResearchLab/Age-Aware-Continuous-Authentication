
import os
import mobile.zone_18_36 as m_zone_18_36
import mobile.zone_54_72 as m_zone_54_72
import mobile.zone_all_ages as m_zone_all_ages

import desktop.special_chars_all_ages as d_specCharAll
import desktop.special_chars_18_36 as d_specChar18_36
import desktop.special_chars_54_72 as d_specChar54_72

import mobile.special_chars_all_ages as m_specCharAll
import mobile.special_chars_18_36 as m_specChar18_36
import mobile.special_chars_54_72 as m_specChar54_72

import desktop.zone_18_36 as d_zone_18_36
import desktop.zone_54_72 as d_zone_54_72
import desktop.zone_all_ages as d_zone_all_ages

path = './Participant_Data'
os.chdir(path)
cwd = os.getcwd()

# # Available participants
# ap = {x: [] for x in os.listdir(cwd)}

################################################################
# SPECIAL CHARACTERS
################################
# Desktop
d_specCharAll.main()
d_specChar18_36.main()
d_specChar54_72.main()
# Mobile
m_specCharAll.main()
m_specChar18_36.main()
m_specChar54_72.main()
################################################################
# ZONES
################################
# Desktop
d_zone_18_36.main()
d_zone_54_72.main()
d_zone_all_ages.main()
# Mobile
m_zone_18_36.main()
m_zone_54_72.main()
m_zone_all_ages.main()


################################ TODO: Add these to seperate files ##################################
# A18_36 = [k for i, (k, v) in enumerate(
#     allageclass.items()) if v >= 18 and v <= 36]
# A54_72 = [k for i, (k, v) in enumerate(
#     allageclass.items()) if v >= 54 and v <= 72]


# # Account Context


# class AC(IntEnum):
#     PERSONAL = 0
#     UTILITY = 1
#     WORK = 2
#     BANKING = 3
#     SCHOOL = 4
#     USR = 0
#     PWD = 1


# allageclass = {'P0002': 32,
#                'P0003': 22,
#                'P0004': 22,
#                'P0005': 32,
#                'P0006': 20,
#                'P0007': 24,
#                'P0008': 23,
#                'P0009': 21,
#                'P0010': 20,
#                'P0011': 68,
#                'P0012': 67,
#                'P0013': 68,
#                'P0014': 71,
#                'P0015': 68,
#                'P0016': 60}


# A18_36 = [k for i, (k, v) in enumerate(
#     allageclass.items()) if v >= 18 and v <= 36]
# A54_72 = [k for i, (k, v) in enumerate(
#     allageclass.items()) if v >= 54 and v <= 72]
# x1 = PreProcData('CA_output_phone', cwd)
# l1 = 'ca_home_act_2.txt'
# d1 = {}

# lst = []


# def j(self):
#     x = []
#     if self.info[0] in A18_36:
#         if exists(self.file):
#             with open(self.file, 'r') as f:
#                 lines = f.readlines()
#                 lines = [i.strip() for i in lines]
#                 if (lines[0] == 'personal_user'):
#                     lines = [lines[f+3]
#                              for f in range(len(lines)) if f % 4 == 0]

#                 else:
#                     lines = [lines[f+1]
#                              for f in range(len(lines)) if f % 2 == 0]

#                 # print(f'{lines}{self.info}')
#                 if not (self.info[0] in list(d1.keys())):
#                     d1[self.info[0]] = {}
#                 d1[self.info[0]][self.info[1]] = {}

#                 for cnt, pwd in enumerate(lines):
#                     results = zxcvbn(pwd)
#                     lst.append(results["score"])
#     self.returnInfo = x


# x1.custom(j, l1)
# print(sum(lst) / len(lst))

# # Account Context


# class AC(IntEnum):
#     PERSONAL = 0
#     UTILITY = 1
#     WORK = 2
#     BANKING = 3
#     SCHOOL = 4
#     USR = 0
#     PWD = 1


# allageclass = {'P0002': 32,
#                'P0003': 22,
#                'P0004': 22,
#                'P0005': 32,
#                'P0006': 20,
#                'P0007': 24,
#                'P0008': 23,
#                'P0009': 21,
#                'P0010': 20,
#                'P0011': 68,
#                'P0012': 67,
#                'P0013': 68,
#                'P0014': 71,
#                'P0015': 68,
#                'P0016': 60}


# A18_36 = [k for i, (k, v) in enumerate(
#     allageclass.items()) if v >= 18 and v <= 36]
# A54_72 = [k for i, (k, v) in enumerate(
#     allageclass.items()) if v >= 54 and v <= 72]
# x1 = PreProcData('CA_output_phone', cwd)
# l1 = 'ca_home_act_2.txt'
# d1 = {}

# lst = []


# def j(self):
#     x = []
#     if self.info[0] in A54_72:
#         if exists(self.file):
#             with open(self.file, 'r') as f:
#                 lines = f.readlines()
#                 lines = [i.strip() for i in lines]
#                 if (lines[0] == 'personal_user'):
#                     lines = [lines[f+3]
#                              for f in range(len(lines)) if f % 4 == 0]

#                 else:
#                     lines = [lines[f+1]
#                              for f in range(len(lines)) if f % 2 == 0]

#                 # print(f'{lines}{self.info}')
#                 if not (self.info[0] in list(d1.keys())):
#                     d1[self.info[0]] = {}
#                 d1[self.info[0]][self.info[1]] = {}

#                 for cnt, pwd in enumerate(lines):
#                     results = zxcvbn(pwd)
#                     lst.append(results["score"])
#     self.returnInfo = x


# x1.custom(j, l1)
# print(sum(lst) / len(lst))
