package me.whiteship.java8to11.인터페이스변화;

public class DefaultFoo implements Foo, Bar{
    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    // Foo, Bar 둘다에 존재하는 메소드는 어떤 것을 쓸지 모르기 때문에 컴파일 에러를 발생시킨다.
    // 이런 경우 직접 Override 해서 재정의 해야한다.
    @Override
    public void printNameUpperCase() {
        System.out.println(this.name.toUpperCase());
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
