package me.whiteship.java8to11;

public class Foo {
    public static void main(String[] args){
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Do it!!");
            }
        };
        runSomething.doIt();

        int baseNumber = 10;
        RunSomething runSomething2 = () -> {
//            baseNumber++;
            System.out.println("Do it!!");
        };
        runSomething2.doIt();
    }
}
