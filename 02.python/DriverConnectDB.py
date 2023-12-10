"""
MySQL DataBase Connection
pip install pymysql
"""
import pymysql

conn = pymysql.connect(host='localhost',port=3306,user='root',passwd='1234',db='gitBase')
cur = conn.cursor()
cur.execute("USE gitBase")
cur.execute("SELECT * FROM gitTable")
print(cur.fetchall())
cur.close()
conn.close()