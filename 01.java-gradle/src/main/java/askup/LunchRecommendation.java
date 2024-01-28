package askup;
import java.util.Random;

public class LunchRecommendation {
    public static void main(String[] args) {
        String[] dishes = {"된장찌개", "불고기", "비빔밥", "김치찌개", "제육볶음", "된장국"};
        Random random = new Random();
        String dish = dishes[random.nextInt(dishes.length)];
        System.out.println("추천하는 점심메뉴는 " + dish + "입니다!");
    }
}
