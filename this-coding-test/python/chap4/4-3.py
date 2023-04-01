# 왕실의 나이트 
'''
정원 밖으로는 나갈수 없으며 L자 형태로만 이동 가능 : 수평(수직) 2칸 이동후 수직(수평) 한칸 
8 x 8 좌표 평면상에서 위치가 주어졌을 때 이동할 수 있는 경우의 수를 출력
나이트의 이동 경로를 steps 변수에 넣으면 규칙에 따라 값을 대응 가능
4-1 에선 dx, dy 리스트를 선언하여 이동할 방향을 기록 하엿고 여기선 steps 변수가 dx와 dy 변수의 기능을 대신한다
input a1
output 2
'''

# 현재 나이트의 위치 입력받기
input_data = input() # ex a1
row = int(input_data[1]) # 슬라이싱 행값을 가져옴
column = int(ord(input_data[0])) - int(ord('a')) + 1 # ord : ASCII 코드 변환 a : 97

# 나이트가 이동할 수 있는 8가지 방향 정의
steps = [(-2,-1), (-1,-2), (1,-2) , (2,-1) , (2,1) , (1,2) , (-1,2) , (-2,1)] 
result = 0
for step in steps:
    # 이동하고자 하는 위치 확인
    next_row = row + step[0]
    next_column = column + step[1]
    # 해당 위치로 이동이 가능하다면 카운트 증가
    if next_row >= 1 and next_row <= 8 and next_column >= 1 and next_column <= 8: 
        result += 1

print(result)