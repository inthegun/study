package programmers;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author lsm
 * @since 2022.08.01
 * 프로그래머스 전화번호 목록
 * @link https://school.programmers.co.kr/learn/courses/30/lessons/42578
 */
public class TelList {
    
    /**
     * Sorting 과 Loop 
     * @param phone_book
     * @return
     */
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        int size = phone_book.length;
        
        for(int i =1;i<size;i++){
            if(phone_book[i].startsWith(phone_book[i-1])){
                answer = false;
            }
        }
        return answer;
    }

    /**
     * HashMap 을 이용
     * @param phone_book
     * @return
     */
    public boolean solution2(String[] phone_book) {
        boolean answer = true;
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0; i < phone_book.length; i++) {
            map.put(phone_book[i], 1);
        }
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 1; j < phone_book[i].length(); j++) {
                if(map.containsKey(phone_book[i].substring(0,j))){
                    return false;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        // String[] part = {"leo", "kiki", "eden"};
        // String[] comp = {"eden", "kiki"};
        String[] case1 = {"119", "97674223", "1195524421"};
        String[] case2 = {"123", "456", "789"};
        String[] case3 = {"12", "123", "1235", "567", "88"};

        TelList sol = new TelList();

        System.out.println(sol.solution(case1));
        System.out.println(sol.solution2(case1));

    }
}