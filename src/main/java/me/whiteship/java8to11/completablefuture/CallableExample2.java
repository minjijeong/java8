package me.whiteship.java8to11.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CallableExample2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Callable<String> spring = () -> {
            Thread.sleep(1000L);
            return "spring";
        };
        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "java";
        };
        Callable<String> keesun = () -> {
            Thread.sleep(5000L);
            return "keesun";
        };
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "hello";
        };

        // 각 callable 끝날때까지 기다림.
        // 가장 긴 keesun이 끝날때까지 기다린다.
        // 제일 먼저 끝나는 작업이 무엇이든 나머지 친구들은 유사하게 끝남..
        List<Future<String>> futureList = executorService.invokeAll(Arrays.asList(keesun,spring, java,hello));

        int idx = 0;
        long before = System.currentTimeMillis();
        System.out.println("before :: "+ before);
        for (Future<String> future : futureList) {
            double end = (System.currentTimeMillis() - before) / 1000.0;
            System.out.printf(future.get());
            System.out.printf(" :: futureList[%d] - end :: %f \n",idx, end);
            idx++;
        }
        /**
         * keesun :: futureList[0] - end :: 0.018000
         * spring :: futureList[1] - end :: 0.026000
         * java :: futureList[2] - end :: 0.026000
         * hello :: futureList[3] - end :: 0.026000
         */
        /**
         * 서버 3대인데, 똑같은 파일 가져오라고 했는데 각 서버에 다 똑같은 파일(copy)를 가지고 있다.
         * 이때 어느 서버든 먼저 도착하는애 착안하면 되는 상황
         * -> invokeAny 사용
         */
        // ** 쓰레드가 List 개수보다 작은 경우, List 넣은 순서대로 큐에 쌓이므로.. 정해진 쓰레드에 먼저 적재된 작업중에 짧은것 리턴
        String s = executorService.invokeAny(Arrays.asList(hello, java,keesun,spring));
        System.out.println(s);
        // [출력] spring  -> sleep 제일 짧음..

        executorService.shutdown();


        String result = Stream.of("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea")
                .filter(w -> w.length() > 1)
                .map(String::toUpperCase)
                .map(w -> w.substring(0, 1))
                .collect(Collectors.joining(" "));
        System.out.println(result);

    }
}
