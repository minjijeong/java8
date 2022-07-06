package me.whiteship.java8to11.completablefuture;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample {
    public static void main(String[] args){
        // 1개의 쓰레드로 실행
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 쓰레드 개수지정하여 실행
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        /**
         * main -> executorService -> 쓰레드 2개 세팅
         * 각 쓰레드 안에 BlockingQueue 존재, 각 쓰레드에 각 작업을 쌓아놓음
         * 쓰레드풀을 사용하면, 추가적으로 쓰레드를 생성하지 않아도 되기때문에 비용절감 된다.
         *
         * [출력] 쓰레드를 2개로 지정하여 2개로 나뉘어 처리
         * Java pool-1-thread-2
         * The pool-1-thread-1
         * Thread pool-1-thread-1
         * Executor pool-1-thread-1
         */
//        executorService.submit(() -> {
//            System.out.println("Hello : "+ Thread.currentThread().getName());
//        });
        executorService.submit(getRunnable("The"));
        executorService.submit(getRunnable("Java"));
        executorService.submit(getRunnable("Thread"));
        executorService.submit(getRunnable("Executor"));
        /**
         * ExcutorService는 명시적으로 shutdown 하기 전까지 계속 대기
         */
        // 처리중인 작업 기다렸다가 종료
        // Graceful shoutdown
        executorService.shutdown();
//        executorService.shutdownNow(); // 당장 종료

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Schedule"),1,2, TimeUnit.SECONDS);
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }
}
