package me.whileship.java8to11.스트림API;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.whiteship.java8to11.model.OnlineClass;

public class StreamAPI예제 {
    public static void main(String[] args){
        List<OnlineClass> springClass=  new ArrayList<>();
        springClass.add(new OnlineClass(1,"spring boot", true));
        springClass.add(new OnlineClass(2,"spring data", true));
        springClass.add(new OnlineClass(3,"spring mvc", false));
        springClass.add(new OnlineClass(4,"spring core", false));
        springClass.add(new OnlineClass(5,"rest api", false));

        List<OnlineClass> javaClass =  new ArrayList<>();
        javaClass.add(new OnlineClass(6,"the java, test", true));
        javaClass.add(new OnlineClass(7,"the java, code manipulation", true));
        javaClass.add(new OnlineClass(8,"the java, 8 to 11", false));

        List<List<OnlineClass>> events = new ArrayList<>();
        events.add(springClass);
        events.add(javaClass);

        System.out.println("spring 으로 시작하는 수업");
        // TODO
        springClass.stream().filter(s-> s.getTitle().startsWith("spring")).forEach(s -> System.out.println(s.getTitle()));

        System.out.println("close 되지 않은 수업");
        // TODO
        springClass.stream().filter(s-> s.isClosed()).forEach(s -> System.out.println(s.getTitle()));
        // mathod reference & static method 활용
        springClass.stream().filter(Predicate.not(OnlineClass::isClosed)).forEach(s -> System.out.println(s.getTitle()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        // TODO
        springClass.stream().map(OnlineClass::getTitle).forEach(System.out::println);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // TODO
        // flatMap -> 리스트안에 있는 애들 다 꺼낸다는 의미
        events.stream().flatMap(Collection::stream)
                .forEach(oc -> System.out.println(oc.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        // TODO
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        // TODO
        boolean test = javaClass.stream().anyMatch(s -> s.getTitle().contains("test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        // TODO
        List<String> springList = springClass.stream().filter(s -> s.getTitle().contains("spring"))
                .map(s -> s.getTitle())
                .collect(Collectors.toList());
        springList.forEach(System.out::println);

    }
}
