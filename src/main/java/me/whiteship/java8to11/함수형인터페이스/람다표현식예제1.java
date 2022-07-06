package me.whiteship.java8to11.함수형인터페이스;

import java.util.function.Function;

public class 람다표현식예제1 {
    public static void main(String[] args){
        /**
         * 람다표현식 라인이 1줄이하라면 {} 괄호, return 없이 사용가능
         */
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Do it!!");
            }
        };
        runSomething.doIt();

        RunSomething runSomething2 = () -> System.out.println("Do it!!");
        runSomething2.doIt();

        /**
         * 람다 표현식 라인이 2개이상 {} 괄호로 표기하여 표현
         */
        Function<String, Integer> convert = s -> {
            if(s.length() > 0){
                return Integer.valueOf(s);
            }
            return 0;
        };
        System.out.println(convert.apply("")); // 0 출력
        System.out.println(convert.apply("5")); // 5 출력
    }

}
