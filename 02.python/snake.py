# 파이썬으로 지렁이 게임을 만들기 위해서는 `pygame` 모듈과 기본적인 파이썬 문법, 로직을 이해해야 합니다. 아래는 간단한 지렁이 게임을 만드는 방법입니다.


import pygame, random

pygame.init()
screen = pygame.display.set_mode((400, 400))

snake = [(200, 200),(210, 200),(220, 200)] # 초기 뱀 위치
dx, dy = 10, 0 # 초기 이동 방향
apple = (random.randint(0, 39) * 10, random.randint(0, 39) * 10) # 초기 사과 위치

while True:
    pygame.time.delay(100) # 게임의 딜레이를 조절
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            
    keys = pygame.key.get_pressed() # 키보드 입력 감지
    if keys[pygame.K_LEFT]:
        dx, dy = -10, 0
    elif keys[pygame.K_RIGHT]:
        dx, dy = 10, 0
    elif keys[pygame.K_UP]:
        dx, dy = 0, -10
    elif keys[pygame.K_DOWN]:
        dx, dy = 0, 10

    new_head = (snake[-1][0]+dx, snake[-1][1]+dy) # 새로운 머리 위치
    if new_head == apple: # 사과 먹었을 때
        apple = (random.randint(0, 39) * 10, random.randint(0, 39) * 10)
    else:
        snake.pop(0) # 꼬리 제거

    snake.append(new_head) # 뱀 머리 추가

    screen.fill((0,0,0)) # 배경 색 설정
    pygame.draw.rect(screen, (255,0,0), (apple[0], apple[1], 10, 10)) # 사과 생성
    for pos in snake:
        pygame.draw.rect(screen, (255,255,255), (pos[0], pos[1], 10, 10)) # 뱀 생성

    if (new_head[0] < 0 or new_head[0] > 390 or new_head[1] < 0 or new_head[1] > 390 or 
       (new_head in snake[:-1])): # 충돌 처리
        font = pygame.font.SysFont("Arial", 50)
        text = font.render("GAME OVER", True, (255, 255, 255))
        screen.blit(text, (80, 170))
        pygame.display.update()
        pygame.time.delay(2000)
        pygame.quit()

    pygame.display.update()


# 위 코드는 파이썬의 `pygame` 모듈을 이용한 지렁이 게임 코드입니다. 뱀의 이동 방향을 조절하며, 사과를 먹어서 몸을 늘리는 게임입니다. 충돌 처리를 통해 게임이 종료되고, 다시 시작할 수 있습니다. 이 코드를 참고해서 더욱 많은 요소를 추가하고, 게임 기능을 업그레이드할 수 있습니다.
