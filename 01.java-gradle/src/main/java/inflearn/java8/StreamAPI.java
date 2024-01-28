package inflearn.java8;

import java.util.stream.Stream;

public class StreamAPI {

//    List<>

    public static void main(String[] args) {
        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10,i -> i +1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
//        .stream().

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List 로 만들기");
//        .stream().filter(oc -> oc.getTitle().contains("spring"))
//        .map(OnlineClass::getTitle)
//        .collect(Collectors.toList());
    }
}