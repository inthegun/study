# 위에서 아래로
'''
크기에 상관없이 나열된 수열
이 수를 큰수부터 작은 수의 순서로 정렬 
input 
3     -> N 개수
15
27
12
output
27 15 12
선택,삽입,퀵,계수 아무거나 이용해도 되지만 코드가 간결해지는 기본 정렬 라이브러리 이용
'''
# N 을 입력받기
n = int(input())

# N개의 정수를 입력받아 리스트에 저장
array = []
for i in range(n):
    array.append(int(input()))

# 파이썬 기본 정렬 라이브러리를 이용하여 정렬 수행
array = sorted(array,reverse=True)

# 정렬이 수행된 결과를 출력
for i in array:
    print(i,end=' ')
