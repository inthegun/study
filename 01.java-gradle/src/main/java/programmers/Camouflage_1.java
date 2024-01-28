package programmers;

import java.util.HashMap;

/**
 * @author lsm
 * @since 2022.08.01
 * 프로그래머스 위장
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/42578
 */
public class Camouflage_1 {
    /**
     * 
     * @param clothes
     * @return
     */
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String,Integer> map = new HashMap<>();

        for(String[] key : clothes){
            if(map.containsKey(key[1])){
                map.replace(key[1], map.get(key[1]).intValue()+1);
            }else{
                map.put(key[1], map.getOrDefault(key,0)+1);
            }
        }

        for (String key : map.keySet()) {
            answer *= (map.get(key)) + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        // 의상 이름 , 의상 종류 
        String[][] case1 = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        Camouflage_1 sol = new Camouflage_1();

        System.out.println(sol.solution(case1));

    }
}