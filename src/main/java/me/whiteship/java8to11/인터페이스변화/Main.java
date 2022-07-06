package me.whiteship.java8to11.인터페이스변화;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;

public class Main {
    public static void main(String[] args){
        // 인터페이스 상속받은 클래스 객체 선언하여 접근
        Foo foo = new DefaultFoo("foo");
        foo.printName();
        foo.printNameUpperCase();

        // static으로 선언된 메소드는 직접 접근 가능하다.
        Foo.printAnything();

        /**
         * Iterable의 default 메소드
         * - forEach()
         * - spliterator()
         */
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");
        System.out.println("=forEach===============");
        // 람다
        names.forEach(s->{
            System.out.println(s);
        });

        System.out.println("=forEach=short=============");
        // 메소드 레퍼런스
        names.forEach(System.out::println);

        System.out.println("=for===============");
        // 기존 for문
        for(String name : names){
            System.out.println(name);
        }

        /**
         * spliterator()
         * iterable와 비슷한데, 쪼갤수 있는 기능을 가지고 순환하는데 사용함
         */
        System.out.println("=spliterator===============");
        Spliterator<String> spliterator = names.spliterator();
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("================");
        // 출력
        // keesun
        // whiteship
        // toby
        // foo
        // 순서와는 상관없이 쪼개어서 순환한다.
        Spliterator<String> spliterator1 = names.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();
        while(spliterator1.tryAdvance(System.out::println));
        System.out.println("---------------");
        while (spliterator2.tryAdvance(System.out::println));
        // 출력
        // toby
        // foo
        // ---------------
        // keesun
        // whiteship

        /**
         * List는 Collection의 자식 클래스 이므로 Collection의 모든 기능을 사용가능
         */
        System.out.println("=[stream]=============");
        List<String> newNames = names.stream()
                .filter(s-> s.startsWith("f"))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(newNames);
        // 출력 [FOO]
        System.out.println("=[removeIf]=============");
        names.removeIf(s-> s.startsWith("k"));
        System.out.println(names);
        // 출력 [whiteship, toby, foo] keesun 삭제됨!
        System.out.println("=[parallelSream]=============");
        List<String> pNewNames = names.parallelStream()
                                        .filter(s-> s.startsWith("f"))
                                        .map(s -> s.toUpperCase())
                                        .collect(Collectors.toList());
        System.out.println(pNewNames);
        // 출력 [FOO]
        /**
         * Comparator
         * - reversed()
         * - thenComparing()
         * - static reverseOrder()/ naturalOrder()
         * - static nullsFirst() / nullsLast()
         */
        System.out.println("==============");

        Comparator<String> compareToIngnoreCase = String::compareToIgnoreCase;
        Comparator<String> compareTo = String::compareTo;
        // reversed 역순으로
        names.sort(compareToIngnoreCase.reversed());
        System.out.println(names);
        // [whiteship, toby, foo]

        // thenComparing 2개의 Comparator 연결
        names.sort(compareToIngnoreCase.thenComparing(compareTo));
        System.out.println(names);
        // [foo, toby, whiteship]

        System.out.println("==============");
        // static reverseOrder()/ naturalOrder()
        String[] ppl = {"handsome", "pretty", "hot", "omg"};
//        Arrays.sort(ppl);
        System.out.println(Arrays.toString(ppl));
        // 출력 [handsome, hot, omg, pretty]

        // 특정 인덱스만 sort
        Arrays.sort(ppl,1,3);
        System.out.println(Arrays.toString(ppl));
        // 출력 [handsome, hot, pretty, omg]

        // static 메소드이므로 인터페이스명.메소드명 바로 사용가능
        Arrays.sort(ppl, Comparator.reverseOrder());
        System.out.println(Arrays.toString(ppl));
        // 출력 [pretty, omg, hot, handsome]

        Arrays.sort(ppl, Comparator.naturalOrder());
        System.out.println(Arrays.toString(ppl));
        // 출력 [handsome, hot, omg, pretty]

        String[] factors = {"handsome", "pretty", "hot", "omg", null, ""};
        // nullsFirst
        Arrays.sort(factors, Comparator.nullsFirst(Comparator.reverseOrder()));
        System.out.println(Arrays.toString(factors));
        // 출력 [null, pretty, omg, hot, handsome, ]

        // nullsLast
        Arrays.sort(factors, Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println(Arrays.toString(factors));
        // 출력 [, handsome, hot, omg, pretty, null]

        // sort 규칙 여러개 지정 가능 (thenComparing)
        Comparator<String> nullsLast = Comparator.nullsLast(Comparator.reverseOrder());
        nullsLast.thenComparing(s -> {
            if(Strings.isNotBlank(s)) {
                return s.toUpperCase();
            }
            return s;
        });
        Arrays.sort(factors, nullsLast);
        System.out.println(Arrays.toString(factors));
        // 출력 [, null, handsome, hot, omg, pretty]
    }
}
