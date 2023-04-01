# 시각
'''
정수 N이 입력되면 00시 00분 00초 ~ N시 59분 59초 까지의 모든 시각 중 3이 하나라도 포함되는 모든 경우의 수
시,분,초에 대한 경우의 수는 24 * 60 * 60 이며 3중 반복문을 이용해 계산 가능
이러한 유형은 완전 탐색 유형 
input 5
output 11475
'''

# H를 입력받기
h = int(input())

count = 0
for i in range(h+1):
    for j in range(60):
        for k in range(60):
            # 매 시각 안에 '3'이 포함되어 있다면 카운트 증가
            if '3' in str(i) + str(j) + str(k):
                count += 1

print(count)