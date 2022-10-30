import pymysql
import csv

# Connection
con = pymysql.connect(host='localhost',
                      user='root',
                      password='',
                      db='bike',
                      charset='utf8')   # connect method

print("connect successful!!")

cursorObject = con.cursor()  # cursor를 이용한다

# SQL query string
sqlQuery = "select * from bikerental where cnt > 5000"

# Execute the sqlQuery
cursorObject.execute(sqlQuery)  # execute method로 실행

# Fetch all the rows
rows = cursorObject.fetchall()

# Write to CSV file
f = open('practice.csv', 'w', encoding='utf-8', newline='')
wr = csv.writer(f)

wr.writerow(["dteday", 
    "season", 
    "yr",
    "mnth",
    "holiday",
    "weekday",
    "workingday",
    "weathersit",
    "temp",
    "atemp",
    "hum",
    "windspeed",
    "cnt"])

for row in rows:
   wr.writerow([row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12]])
f.close()    
    
# Close connection

con.commit()
con.close()

