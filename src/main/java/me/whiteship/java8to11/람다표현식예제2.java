package me.whiteship.java8to11;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class 람다표현식예제2 {
    public static void main(String[] args){
        람다표현식예제2 ex = new 람다표현식예제2();
        ex.run();
    }

    private void run(){
        // final int baseNumber = 10;
        // 로컬클래스, 익명 메소드에서 지역변수를 참조하기 때문에 사실상 final 이다.
        // effective final = 사실상 final 동일 의미
        int baseNumber = 10;

        // 로컬 클래스
        // 상위 메소드, 클래스와 다른 scope 이므로 매개변수 동일 변수명 선언 가능
        // 로컬 클래스 내부의 지역변수을 따른다. 즉, 쉐도잉 적용 된다.
        class LocalClass{
            void printBaseNumber(){
                int baseNumber = 11;
                System.out.println(baseNumber);
            }
        }
        LocalClass lc = new LocalClass();
        lc.printBaseNumber();
        // 출력 11

        // 익명 클래스
        // 상위 메소드, 클래스와 다른 scope 이므로 매개별수 동일 변수명 선언 가능
        // 익명 클래스의 매개변수을 따른다. 즉, 쉐도잉 적용 된다.
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber);
            }
        };
        integerConsumer.accept(20);
        // 출력 20

        // 람다
        // 람다를 감싸고 있는 클레스, 메소드와 같은 Scope이다
        IntConsumer printInt = (i) -> {
            // int baseNumber = 100
            // 같은 Scope이라서, 동일 변수명을 선언할 수 없다.
            System.out.println(i + baseNumber);
        };
        printInt.accept(10);
        // 출력 10

    }
}
