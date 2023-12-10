package utils;

import java.awt.Robot;

public class RobotApplication {
    
    public static void main(String[] args) throws Exception {
        
        Robot robot = new Robot();

        int x,y;

        while(true){ // 종료할 때 까지 무한 반복
            x = (int) (Math.random() * 1920); // 1920*1080 해상도 내의 x,y 값을 랜덤하게 생성
            y = (int) (Math.random() * 1080);
            
            robot.mouseMove(x, y); // x,y 좌표로 이동

            Thread.sleep(5000); // 5초 대기 후 다음 좌표로 이동
        }
    }
}