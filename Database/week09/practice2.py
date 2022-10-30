import pymysql

# Connection
con = pymysql.connect(host='localhost',
                      user='root',
                      password='',
                      db='mydb',
                      charset='utf8')

print("connect successful!!")

cursorObject = con.cursor()

# SQL update string
sqlUpdate = "update instructor set salary = salary * 1.05;"

# Execute update
cursorObject.execute(sqlUpdate)
    
# Close connection

con.commit()
con.close()