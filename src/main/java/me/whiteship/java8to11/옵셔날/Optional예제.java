package me.whiteship.java8to11.옵셔날;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import me.whiteship.java8to11.model.OnlineClass;
import me.whiteship.java8to11.model.Progress;

public class Optional예제 {
    public static void main(String[] args){
        OnlineClass onlineClass = new OnlineClass(1, "spring boot", true);
        Optional<OnlineClass> opClass = Optional.of(onlineClass);
        System.out.println(opClass.toString());
        // Optional[OnlineClass{id=1, title='spring boot', closed=true, progress=null}]

        OnlineClass nullClass = null;
        Optional<OnlineClass> opNullClass = Optional.ofNullable(nullClass);
        System.out.println(opNullClass.toString());
        // Optional.empty

        Optional<OnlineClass> opEmptyClass = Optional.empty();
        System.out.println(opEmptyClass.toString());
        // Optional.empty

//        Optional<OnlineClass> onlineClass = new OnlineClass(1, "spring boot", true);
        System.out.println(opClass.isPresent());
        System.out.println(opClass.isEmpty());
        // true

        // Optional.ifPresent
//        System.out.println(opEmptyClass.get());
//        System.out.println(opEmptyClass.get().getId());
//        Exception in thread "main" java.util.NoSuchElementException: No value present
//        Consumer<OnlineClass> consumer = new Consumer<OnlineClass>() {
//            @Override
//            public void accept(OnlineClass onlineClass) {
//                if (onlineClass.getTitle().startsWith("spring")) {
//                    System.out.println(onlineClass.getId());
//                }
//            }
//        };

        opClass.ifPresent(s -> {
            if(s.getTitle().startsWith("spring")){
                System.out.println(s.getId());
            }
        });
        // 출력 1

        // Optional.orElse
        Optional<OnlineClass> otherClass = null;
        OnlineClass springDataClass = new OnlineClass(2,"spring data", true);
        // OnlineClass newClass = otherClass.orElse(springDataClass);
        // 출력 Runtime Exception -> Exception in thread "main" java.lang.NullPointerException

        otherClass = Optional.empty();
        OnlineClass newClass = otherClass.orElse(springDataClass);
        System.out.println(newClass);

//        otherClass = Optional.empty();
//        Function<OnlineClass, OnlineClass> func = s->{
//            if(s.getTitle().startsWith("jpa"))
//                return s;
//            return null;
//        };
        List<OnlineClass> springClasses=  new ArrayList<>();
        springClasses.add(new OnlineClass(1,"spring boot", true));
        springClasses.add(new OnlineClass(2,"spring data", true));

        Optional<OnlineClass> optional = springClasses.stream()
                                            .filter(oc -> oc.getTitle().startsWith("spring"))
                                            .findFirst();

        Supplier<OnlineClass> supplier = () -> new OnlineClass(99,"how to study java", true);
        System.out.println(optional.orElseGet(supplier));

        // OnlineClass{id=99, title='how to study java', closed=true, progress=null}

//        //  Optional에 값이 없으면 에러를 던져라.
//        otherClass = Optional.empty();
//        otherClass.orElseThrow();

        Optional<OnlineClass> online = optional.filter(oc-> !oc.isClosed());
        System.out.println(online.isEmpty());
        // 출력 true

        Optional<Integer> id = optional.map(oc -> oc.getId());
        System.out.println(id.isPresent());

        // Optional에서 Optional인 것을 반환 받으면 map을 사용하게되면 복잡해진다.
        // 이를 개선하기 위해 flatMap을 사용하면 한껍질 벗기고 바로 나오게 할수 있다.
//        Optional<Optional<Progress>> mapProgress = optional.map(oc -> oc.getProgress());
//        Optional<Progress> progress = mapProgress.orElseThrow();
//        progress.orElseThrow();
        // 출력 Exception in thread "main" java.util.NoSuchElementException: No value present

        Optional<Progress> flatMapProgress = optional.flatMap(OnlineClass::getProgress);
        System.out.println(flatMapProgress);
        // Optional.empty


    }
}
