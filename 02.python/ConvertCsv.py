"""
Encoding : utf-8
MySQL의 테이블을 읽어서 로컬 csv 파일로 저장한다
Encoding이 UTF-8로 하게되면 Excel에서 한글이 깨지는 경우가 발생할수 있음
csv파일을 메모장에서 ANSI인코딩형식으로 저장하면 정상적으로 표시됨
"""

import csv
import pymysql
import os

'''
VSCODE로 python을 실행할경우 상대경로(파일경로)를 제대로 인식하지 못한다.
현재 경로를 os로 가져와서 변수에 담음
'''
path = os.path.abspath(__file__)
dir_path = os.path.dirname(path)
host = '127.0.0.1'
port = 3306
user = 'root'
password = '1234'
db = 'gitBase'
conn = pymysql.connect(host=host,port=port,user=user,password=password,db=db)
cur = conn.cursor()

cur.execute('SELECT * FROM MEMBER')
# print(dir_path)
csvFile = open(dir_path+'\\files\\member.csv','w+',newline='',encoding='utf-8')
try:
    writer = csv.writer(csvFile)
    writer.writerow(('id','password','name','email','phone','job','regdate'))
    for i in range(3):
        writer.writerow(cur.fetchone())
    print('----End Write----')
finally:
    cur.close()
    conn.close()
    csvFile.close()

'''
실행 결과
id,password,name,email,phone,job,regdate
gitUser,4321,깃유저,git@user.com,010-1234-5678,,2022-02-28 19:09:15
inthegun,1234,인디건,inthe@gun.com,010-5926-2361,job,2022-02-28 19:07:42
seoulUser,2341,서울유저,Seoul@user.com,010-8765-4321,student,2022-02-28 19:09:16
'''