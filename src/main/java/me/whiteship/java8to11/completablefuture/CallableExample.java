package me.whiteship.java8to11.completablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // Runnable과 달리 return 값 가질수 있음
        Future<String> helloFuture = executorService.submit(() ->{
           Thread.sleep(3000L);
           return "Callable";
        });
        System.out.println("Hello");

        String result = helloFuture.get();
        System.out.println(result);
        /**
         * [출력]
         * Hello
         * Callable (3초후 출력)
         */
        executorService.shutdown();

        System.out.println("------------------");
        ExecutorService otherExecutorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () ->{
            Thread.sleep(2000L);
            return "Hello Callable~ ";
        };
        Future<String> helloOtherFuture = otherExecutorService.submit(hello);
        System.out.println("helloOtherFuture.isDone: " + helloOtherFuture.isDone());
        System.out.println("Started!!");

//        helloOtherFuture.get();
        // interrupt로 던지려면 true로 세팅
//        helloOtherFuture.cancel(true);
        System.out.println("helloOtherFuture.isDone: " + helloOtherFuture.isDone());

        helloOtherFuture.get();
        // get이전에 cancel을 하면 get 처리시 에러 발생
        // Exception in thread "main" java.util.concurrent.CancellationException
        //	at java.base/java.util.concurrent.FutureTask.report(FutureTask.java:121)
        //	at java.base/java.util.concurrent.FutureTask.get(FutureTask.java:191)

        // get 보다 강제종료를 먼저 실행시, sleep을 마저 처리하지 못하고 interruptedException으로 빠짐
        // Caused by: java.lang.InterruptedException: sleep interrupted
        //	at java.base/java.lang.Thread.sleep(Native Method)
//         otherExecutorService.shutdownNow();

        System.out.println("helloOtherFuture.isDone: " + helloOtherFuture.isDone());
        System.out.println("End!!");
        otherExecutorService.shutdown();

        /**
         * [출력]
         * helloOtherFuture.isDone: false
         * Started!!
         * helloOtherFuture.isDone: false
         * // Get 실행
         * helloOtherFuture.isDone: true
         * End!!
         */
    }
}
