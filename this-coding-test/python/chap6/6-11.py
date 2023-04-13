# 성적이 낮은 순서로 학생 출력하기
'''
N명의 학생정보 
학생의 이름과 학생의 성적
성적이 낮은 순서대로 이름을 출력
input
2
홍길동 95
이순신 77
output 이순신 홍길동

'''

# N을 입력받기
n = int(input())

# N명의 학생 정보를 입력받아 리스트에 저장
array = []
for i in range(n):
    input_data = input().split()
    # 이름은 문자열 그대로 , 점수는 정수형으로 변환하여 저장
    array.append((input_data[0], int(input_data[1])))

# Key(키)를 이용하여, 점수를 기준으로 정렬
array = sorted(array, key=lambda student: student[1])

# 정렬이 수행된 결과를 출력
for student in array:
    print(student[0], end=' ')