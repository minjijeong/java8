package me.whiteship.java8to11.인터페이스변화;

public interface Bar extends Foo{

    // Foo에서 정의된 메소드를 다시 추상메소드로 변경 가능
    void printNameUpperCase();
}
