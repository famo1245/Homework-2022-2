import pymysql
import csv

# Connection
con = pymysql.connect(host='localhost',
                      user='root',
                      password='',
                      db='mydb',
                      charset='utf8')   # connect method

print("connect successful!!")

cursorObject = con.cursor()  # cursor를 이용한다

# # SQL insert string
# sqlInsert = "insert into instructor values ('2', 'temp', 'Comp. Sci.', 65000);"

# # Execute
# cursorObject.execute(sqlInsert)

# SQL query string
sqlQuery = "select ID, name, salary from instructor"

# Execute the sqlQuery
cursorObject.execute(sqlQuery)  # execute method로 실행

# Fetch all the rows
rows = cursorObject.fetchall()

# show result of query
for row in rows:
    # print(row)    # row는 list 안에 list가 있는 형태
    print(row[0], ",", row[1], ",", row[2])

# SQL query string
sqlQuery = "select ID, name, salary from instructor where salary>70000"

# Execute the sqlQuery
cursorObject.execute(sqlQuery)

# Fetch all the rows
rows = cursorObject.fetchall()

# Write to CSV file
f = open('mysql_output.csv', 'w', encoding='utf-8', newline='')
wr = csv.writer(f)

for row in rows:
   wr.writerow([row[0], row[1], row[2]])
f.close()    
    
# Close connection

con.commit()
con.close()

