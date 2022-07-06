package me.whileship.java8to11.스트림API;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Stream
 * 연속된 데이타의 오퍼레인션의 모음
 */
public class StreamAPI {
    public static void main(String[] args){
        List<String> names = new ArrayList<>();
        names.add("keesun");
        names.add("whiteship");
        names.add("toby");
        names.add("foo");

        // 중계형 오퍼레이션은 터미널오퍼레이션에 도달하기 전까지 실행이 되지 않는다.
        List<String> collect = names.stream()
                                    .map(s ->{
                                                System.out.println(s);
                                                return s.toUpperCase();
                                    }) // 여기까지는 stream을 리턴해주므로, 중계오퍼레이션 처리
               .collect(Collectors.toList()); // collect를 통해 종료하므로, 터미널 오퍼레이션 처리
        // [keesun,whiteship,toby,foo]
        collect.forEach(System.out::println);
        // [KEESUN, WHITESHIP, TOBY, FOO]

        System.out.println("====================");
        // 원본소스
        names.forEach(System.out::println);
        // [keesun,whiteship,toby,foo]

        System.out.println("====================");
        // 병렬처리
        List<String> collect_prallel = names.parallelStream().map(s ->{
                    System.out.println(s + " " + Thread.currentThread().getName());
                    return s.toUpperCase();
                }) // 여기까지는 stream을 리턴해주므로, 중계오퍼레이션 처리
                .collect(Collectors.toList());
        collect_prallel.forEach(System.out::println);
        /**
         * 출력결과
         * - 각자 다른 쓰레드에서 실행되는 것 확인.
         * foo ForkJoinPool.commonPool-worker-5
         * toby main
         * whiteship ForkJoinPool.commonPool-worker-3
         * keesun ForkJoinPool.commonPool-worker-7
         */
    }
}
