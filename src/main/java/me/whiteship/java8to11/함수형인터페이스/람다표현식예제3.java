package me.whiteship.java8to11.함수형인터페이스;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import me.whiteship.java8to11.model.Greeting;

public class 람다표현식예제3 {
    public static void main (String[] args){
        // UnaryOperator<String> hi = (s) -> "hi" + s;
        // 스태틱 메소드 참조 -> 타입 :: 스태틱메소드
        UnaryOperator<String> hi = Greeting:: hi;
        System.out.println(hi.apply(", hi"));
        // 출력 hi , hi

        // 특정 객체의 인스턴스 메소드 참조 -> 객체 레퍼런스 :: 인스턴스 메소드
        Greeting greeting = new Greeting();
        UnaryOperator<String> hi2 = greeting::hello;
        System.out.println(hi2.apply(", test"));
        // 출력 hello , test

        // 생성자 참조
        Supplier<Greeting> supplier = Greeting::new; // 아직 객체 미생성
        Greeting greeting1 = supplier.get(); // 객체 생성
        System.out.println(greeting1.hello(", supplier"));
        // 출력 hello , supplier

        // 매개변수 있는 생성자 참조
        Function<String, Greeting> keesunGreeting = Greeting::new;
        Greeting keesun = keesunGreeting.apply("keesun");
        System.out.println(keesun.getName());
        // 출력 keesun

        // 임의 객체의 인스턴스 메소드 참조
        String[] names = {"keesun", "whiteship", "toby"};
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        System.out.println(Arrays.toString(names));
        // 출력 [keesun, toby, whiteship]
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
        // 출력 [keesun, toby, whiteship]
    }
}
