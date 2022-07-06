package me.whiteship.java8to11.옵셔날;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import me.whiteship.java8to11.model.OnlineClass;

public class OptionalAPI {
    public static void main(String[] args){
        List<OnlineClass> springClass=  new ArrayList<>();
        springClass.add(new OnlineClass(1,"spring boot", true));
        springClass.add(new OnlineClass(2,"spring data", true));
        springClass.add(new OnlineClass(3,"spring mvc", false));
        springClass.add(new OnlineClass(4,"spring core", false));
        springClass.add(new OnlineClass(5,"rest api", false));

        OnlineClass springBoot = new OnlineClass(1, "spring boot", true);
        Duration studyDuration = springBoot.getProgress().get().getStudyDuration();
        System.out.println(studyDuration);
        /**
         * 이전, 레퍼런스 변수가 null인 경우 처리방법
         * 1. exception 처리 -> model 쪽에서 처리
         * 2. client 쪽에서 null check 해서 로직 처리
         */
        // runtime exception은 좀 낫지만, checked exception이 발생하면 소스 컴파일 자체가 안됨..
        //if(this.progress == null){
        //    throw new IllegalStateException();
        //}
        /**
         * Optional
         * - java8에 추가
         * - null을 return하는 것을 방지할수 있다.
         */

        // boxing , unboxing이 발생해서 성능상 안좋음
        // Optional.of(10);
        // 차라리 OptionalInt를 사용하는것이 좋다.
        OptionalInt.of(10);
    }
}
