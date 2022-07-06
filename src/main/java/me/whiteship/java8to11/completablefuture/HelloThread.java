package me.whiteship.java8to11.completablefuture;

public class HelloThread extends Thread{
    @Override
    public void run() {
        System.out.println("world : " + Thread.currentThread().getName());
    }
}
