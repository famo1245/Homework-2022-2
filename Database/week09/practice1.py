import pymysql

# Connection
con = pymysql.connect(host='localhost',
                      user='root',
                      password='',
                      db='mydb',
                      charset='utf8')

print("connect successful!!")

cursorObject = con.cursor()

# SQL query string
sqlQuery = "select name from instructor where dept_name = 'Comp. Sci.' and salary > 7000"

# Execute the sqlQuery
cursorObject.execute(sqlQuery)  # execute method로 실행

# Fetch all the rows
rows = cursorObject.fetchall()

print("1번")
# show result of query
for row in rows:
    print(row[0])

print("2번")
# SQL query string
sqlQuery = "select name, course_id from instructor, teaches where instructor.ID = teaches.ID and instructor.dept_name = 'Biology'"

# Execute the sqlQuery
cursorObject.execute(sqlQuery)  # execute method로 실행

# Fetch all the rows
rows = cursorObject.fetchall()

# show result of query
for row in rows:
    print(row[0], ",", row[1])
    
# Close connection

con.commit()
con.close()