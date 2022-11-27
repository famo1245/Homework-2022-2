import pymysql

# Connection
con = pymysql.connect(host='localhost',
                      user='root',
                      password='root',
                      db='moviedb',
                      charset='utf8')

cursorObject = con.cursor()
print("connect successful!!")

# Read Data file
fp = open("C:\\data\\title_data.txt", "r", encoding='UTF-8')
curNum = 0

# Read each line from datafile and insert it to MovieTitle
for line in fp.readlines():
    valueList = line.strip().split("\t")

    sqlInsert = "Insert into MovieTitle_1 values ("

    for curValue in valueList:
        if (len(curValue) > 255):
            curValue = curValue[0:255]
        sqlInsert  = sqlInsert + "'" + curValue.replace("\'", "") + "', "

    sqlInsert = sqlInsert[0:len(sqlInsert) -2] + ")"
    cursorObject.execute(sqlInsert)

    sqlInsert = sqlInsert.replace("MovieTitle_1", "MovieTitle_2")
    cursorObject.execute(sqlInsert)

    curNum = curNum  + 1
    if ((curNum % 10000) ==0):
        print(curNum)

fp.close()
con.commit()
con.close()
