package programmers;

import java.util.HashMap;

/**
 * @author lsm
 * @since
 * 프로그래머스 완주하지 못한 선수
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/42576
 */
public class Solution_Hash_1 {

    /**
     *
     * @param participant
     * @param completion
     * @return
     */
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String,Integer> map = new HashMap<>();

        for (String name : participant){
            map.put(name,map.getOrDefault(name,0)+1);
        }
        for (String name : completion){
            map.put(name,map.get(name)-1);
        }

        for (String key : map.keySet()){
            if (map.get(key) > 0){
                answer = key;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] part = {"leo", "kiki", "eden"};
        String[] comp = {"eden", "kiki"};
        Solution_Hash_1 sol = new Solution_Hash_1();
        System.out.println(sol.solution(part,comp));
    }
}