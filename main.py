# -*- coding: utf-8 -*-

import pymysql
from flask import Flask, json, jsonify, request,Response
app = Flask(__name__)

# MySQL 연결 설정
conn = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',
    db='jinju',
    charset='utf8'
)

# MySQL 연결 확인
if conn.open:
    print('connected')
else:
    print('disconnected')


#데이터 flask에 넣기
@app.route('/ab')
def dbmain():
    try:
        with conn.cursor() as curs:
            sql = "SELECT * FROM user_db"
            curs.execute(sql)
            rows = curs.fetchall()
            return json.dumps(rows, ensure_ascii=False)
    except:
        return "Error: Unable to fetch data from database"

@app.route('/abc')
def dbtoilet():
    try:
        with conn.cursor() as curs:
            sql = "SELECT * FROM toilet"
            curs.execute(sql)
            rows = curs.fetchall()
            columns = [desc[0] for desc in curs.description]
            result = []
            for row in rows:
                row = dict(zip(columns, row))
                result.append(row)

            response_data = json.dumps(result, ensure_ascii=False)
            response = Response(response=response_data, content_type="application/json; charset=utf-8")
            return response
    except:
        return "Error: Unable to fetch data from the database"


#데이터 Mysql넣기 코드
@app.route('/insert', methods=['POST', 'GET'])
def toiletsend():
    curs = conn.cursor()
    with open('C:/Users/kmg00/Desktop/DATA/toilet.json', 'r', encoding='utf-8') as f:
        json_data = json.load(f)
        toilets = json_data['toilet']

        for toilet in toilets:
            sql = "INSERT INTO toilet (`화장실ID`,`위도`,`경도`,`화장실이름`,`위치지도별주소`,`위치번호주소`,`남녀공용화장실여부`,`남성용-대변기수`,`남성용-소변기수`,`남성용-장애인용대변기수`,`남성용-장애인용소변기수`,`남성용-어린이용대변기수`,`남성용-어린이용소변기수`,`여성용-대변기수`,`여성용-장애인용대변기수`,`여성용-어린이용대변기수`,`관리기관명`,`전화번호`,`개방시간`,`비상벨설치여부`,`비상벨설치장소`,`화장실입구CCTV설치유무`) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
            val = (
                toilet["화장실ID"],
                toilet["위도"],
                toilet["경도"],
                toilet["화장실이름"],
                toilet["위치지도별주소"],
                toilet["위치번호주소"],
                toilet["남녀공용화장실여부"],
                toilet["남성용-대변기수"],
                toilet["남성용-소변기수"],
                toilet["남성용-장애인용대변기수"],
                toilet["남성용-장애인용소변기수"],
                toilet["남성용-어린이용대변기수"],
                toilet["남성용-어린이용소변기수"],
                toilet["여성용-대변기수"],
                toilet["여성용-장애인용대변기수"],
                toilet["여성용-어린이용대변기수"],
                toilet["관리기관명"],
                toilet["전화번호"],
                toilet["개방시간"],
                toilet["비상벨설치여부"],
                toilet["비상벨설치장소"],
                toilet["화장실입구CCTV설치유무"]
            )
            curs.execute(sql, val)

    conn.commit()
    return "Success"



app.run(host='0.0.0.0',port=8888, debug=True)

conn.close()
