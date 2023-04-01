# 각 행마다 가장 작은 수를 찾은 뒤에 그 수중에서 가장 큰 수
# 문제이해가 어려움...

'''
각 행에서 가장 작은 수를 찾은 다음  , 그 수 중에서 가장 큰 수를 찾는 방식
가장 작은 원소를 찾는 min() , 2중 반복문 구조
'''
# N , M 을 공백으로 구분하여 입력받기
n , m = map(int , input().split())

result = 0 
# 한 줄씩 입력받아 확인
for i in range(n):
    data = list(map(int, input().split()))
    # 현재 줄에서 '가장 작은 수' 찾기
    min_value = min(data)
    # '가장 작은 수'들 중에서 가장 큰수 찾기
    result = max(result, min_value)

print(result) # 최종 답안 출력
