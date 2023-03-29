# 배열의 크기 N , 숫자가 더해지는 횟수 M ,  연속해서 더할수 있는 수 K
# 그리디 알고리즘 

# 가장 큰 수와 두번째로 큰수 저장 
# 연속으로 더할수 있는 횟수는 최대 K번 

# N,M,K 를 공백으로 구분하여 입력받기
n , m , k = map(int, input().split())
# N개의 수를 공백으로 구분하여 입력받기
data = list(map(int,input().split()))

data.sort() # 입력받은 수들 정렬하기
first = data[n -1] # 가장 큰수
second = data[n -2] # 두번째로 큰수

result = 0

while True:
    for i in range(k): # 가장 큰수를 k번 더하기
        if m == 0: # m 이 0이라면 반복문 탈출
            break
        result += first
        m -= 1 # 더할때마다 1씩 빼기
    if m == 0: # m이 0이라면 반복문 탈출
        break
    result += second # 두번째로 큰 수를 한번 더하기
    m -= 1 # 더할때마다 1씩 빼기

print(result) # 최종 답안 출력