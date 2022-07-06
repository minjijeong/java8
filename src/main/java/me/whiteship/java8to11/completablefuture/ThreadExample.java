package me.whiteship.java8to11.completablefuture;

/**
 * 쓰레드를 프로그램으로 직접 관리하기 어렵다.
 *
 */
public class ThreadExample {
    public static void main(String[] args){
        HelloThread helloThread = new HelloThread();
        helloThread.start();
        System.out.println("hello: "+Thread.currentThread().getName());

        Thread thread = new Thread(() ->{
            /**
             * sleep을 걸었기 때문에
             * [출력]
             * Hello: main
             * Thread:Thread-0
             */
//            // 다른 쓰레드가 먼저 일하도록 처리
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Thread:" +Thread.currentThread().getName() );
            /**
             * 무한루프 처리
             * [출력]
             * Thread:Thread-0
             * Hello: main
             * Thread:Thread-0
             * Thread:Thread-0
             */
            while (true){
                System.out.println("Thread:" +Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("exit!");
                    // return을 없애면 interrupt 작용안됨
                    return;
                }
            }
        });
        thread.start();

        System.out.println("Hello: "+Thread.currentThread().getName());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /**
         * 쓰레드를 무한으로 실행 시키는 것을 interrupt하여 종료 시킴
         */
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(thread + " is finished");
        /**
         * Hello: main
         * Thread:Thread-0
         * Thread:Thread-0
         * Thread:Thread-0
         * exit!
         * Thread[Thread-0,5,] is finished
         */
    }
}
