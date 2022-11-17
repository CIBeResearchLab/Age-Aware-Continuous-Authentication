row0 = "`~,1!,2@,3#,4$,5%,6^,7&,8*,9(,0),-_,+="
# modified row0
mr0 = row0.split(",")
row1 = "None,q,w,e,r,t,y,u,i,o,p,{[,}],|\\"
mr1 = row1.split(",")
row2 = "None,a,s,d,f,g,h,j,k,l,;:,'\""
mr2 = row2.split(",")
row3 = "None;z;x;c;v;b;n;m;<,;>.;/?"
mr3 = row3.split(";")
# print(mr3)
x = [[mr0[i] if i < len(mr0) else None, mr1[i] if i < len(mr1) else None, mr2[i] if i < len(
    mr2) else None, mr3[i] if i < len(mr3) else None] for i in range(len(mr1))]


# print(x)
leftZone = x[0:5]
rightZone = x[5:]


def findZone(specChar, prvChar):
    specChar = specChar.lower()
    prvChar = prvChar.lower()
    char1Zone = None
    char2Zone = None
    for col in rightZone:
        for item in col:
            if item:
                if (specChar in item):
                    char1Zone = "RIGHT"
                if (prvChar in item):
                    char2Zone = "RIGHT"
        for col in leftZone:
            for item in col:
                if item:
                    if (specChar in item):
                        char1Zone = "LEFT"
                    if (prvChar in item):
                        char2Zone = "LEFT"
    if (char2Zone == None and char1Zone == "LEFT"):
        # print(prvChar)
        pass
    return f"{char2Zone}->{char1Zone}"


def findDist(specChar, prvChar):
    specChar = specChar.lower()
    prvChar = prvChar.lower()

    spCol = 0
    prvCol = 0
    for col, val in enumerate(x):
        for i in val:
            if i:
                if (specChar in i):
                    spCol = col+1
                if (prvChar in i):

                    prvCol = col+1
    return spCol-prvCol
