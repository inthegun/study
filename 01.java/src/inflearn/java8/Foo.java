package inflearn.java8;

public class Foo {
    public static void main(String[] args) {
        // 람다식 표현
        RunSomething runSomething = () -> System.out.println("Hello");
        // 익명 내부 클래스 anonymous inner class
//        RunSomething runSomething = new RunSomething() {
//            @Override
//            public void doIt() {
//                System.out.println("Hello");
//            }
//        };
        runSomething.doIt();

    }
}