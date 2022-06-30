package me.whiteship.java8to11;

import java.util.function.Function;

public class 람다표현식예제1 {
    public static void main(String[] args){
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
