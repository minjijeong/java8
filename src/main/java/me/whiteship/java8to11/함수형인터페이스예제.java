package me.whiteship.java8to11;

import java.util.function.*;

/**
 * 자바 기본으로 제공하는 함수형 인터페이스
 */
public class 함수형인터페이스예제 {
    public static void main(String[] args) {
        System.out.println("-[Function]----------------------");
        /**
         * Function<T,R>
         * T 타입을 받아서 R 타입을 리턴하는 함수 인터페이스
         */
        // 함수형 인터페이스 상속받은 class 참조
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));
        // 출력 11

        // 람다 익스프레션을 이용하여 바로 사용
        Function<Integer, Integer> plus10_lamda = (i) -> i + 10;
        System.out.println(plus10_lamda.apply(1));
        // 출력 11

        Function<Integer, Integer> multiply2 = (i) -> i*2;
        System.out.println(multiply2.apply(1));
        // 출력 2

        // 함수 조합 가능
        // compose - A.compose(B) = B -> A
        Function<Integer, Integer> multiply2AndPlus10 = plus10_lamda.compose(multiply2); // 고차함수 구현 higher-order-function
        System.out.println(multiply2AndPlus10.apply(1));
        // 출력 12  = (1 * 2) + 10

        // and then - A.andThen(B) = A -> B
        Function<Integer, Integer> multiply2AndthenPlus10 = plus10_lamda.andThen(multiply2);
        System.out.println(multiply2AndthenPlus10.apply(1));
        // 출력 22  = (1 + 10 ) * 2

        System.out.println("-[BiFunction]----------------------");
        /**
         * BiFunction<T,U,R>
         * 두 개의 값 (T,U)을 받아서 R타입을 리턴하는 함수 인터페이스
         * Function과 동일한 기능. 매개변수 차이만 있을뿐
         *  R apply(T t, U u)
         */
        BiFunction<Integer, Integer, String> plusPrint = (m, n) -> String.valueOf(m+n);
        System.out.println(plusPrint.apply(3,5));
        // 출력 8

        System.out.println("-[Consumer]----------------------");
        /**
         * Consumer<T>
         * T 타입을 받아서 아무값도 리턴하지 않는 함수 인터페이스
         * void accept(T var1);
         */
        Consumer<Integer> consumer = i -> System.out.println(i);
        consumer.accept(10);
        // 출력 10;

        System.out.println("-[Supplier]----------------------");
        /**
         * Supplier<T>
         * 매개변수가 없고, 받아올 값만 정의
         */
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());
        // 출력 10

        System.out.println("-[Prdicate]----------------------");
        /**
         * Prdicate<T>
         * T 타입을 받아서 boolean 리턴하는 함수 인터페이스
         */
        Predicate<Integer> isOdd = (i) -> i%2 == 1;
        Predicate<Integer> isLess10 = (i) -> i < 10;
        boolean result = isOdd.and(isLess10).test(11);
        // 11 < 10 = false AND 11 % 2 = 1 true -> false
        System.out.println(result);
        result = isLess10.or(isOdd).test(11);
        // 11 < 10 = false OR 11 % 2 = 1 true -> true
        System.out.println(result);

        System.out.println("-[UnaryOperator]----------------------");
        /**
         * UnaryOperator<T>
         * 입력, 출력이 같은 타입인 경우 사용가능
         */
//        Function<Integer, Integer> plus10_lamda = (i) -> i + 10;
        UnaryOperator<Integer> plus10_unaryop = (i) -> i + 10;
        System.out.println(plus10_unaryop.apply(10));
//        Function<Integer, Integer> multiply2 = (i) -> i*2;
        UnaryOperator<Integer> multiply_unaryop = (i) -> i * 2;
        System.out.println(multiply_unaryop.apply(10));

        System.out.println("-[BinaryOperator]----------------------");
        /**
         * BinaryOperator<T>
         * BiFunction의 특수한 형태로, 세개의 타입모두 같은 타입이라고 가정
         */
//        BiFunction<Integer, Integer, String> plusPrint = (m, n) -> String.valueOf(m+n);
        BinaryOperator<Integer> plusPrint_BO = (m, n)-> m+n;
        System.out.println(plusPrint_BO.apply(3,5));
    }
}
