# 반복되는 수열의 길이는 (K+1) 
# M을 (K+1)로 나눈 몫이 수열이 반복되는 횟수 , K 를 곱해주면 가장 큰수가 등장하는 횟수
# M이 (K+1)로 나누어 떨어지지 않는 경우 , M을(K+1)로 나눈 나머지만큼 가장 큰수가 추가로 더해진다
# 가장 큰 수가 더해지는 횟수 int(M / (K+1)) * K + M % (K + 1) 

# 배열의 크기 N , 숫자가 더해지는 횟수 M ,  연속해서 더할수 있는 수 K
n , m , k = map(int, input().split())
# N개의 수를 공백으로 구분하여 입력받기
data = list(map(int, input().split()))

data.sort() # 입력받은 수 정렬
first = data[n-1] # 가장 큰수
second = data[n-2] # 두번째로 큰수 

# 가장 큰 수가 더해지는 횟수 계산
count = int(m / (k + 1)) * k # 가장 큰수가 등장하는 횟수
count += m % (k + 1 ) # 나누어 떨어지지 않는 경우

result = 0
result += (count) * first # 가장 큰 수 더하기
result += (m - count) * second # 두번째로 큰 수 더하기

print(result) # 최종 답안 출력

