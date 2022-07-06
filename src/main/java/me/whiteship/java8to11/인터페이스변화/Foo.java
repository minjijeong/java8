package me.whiteship.java8to11.인터페이스변화;

public interface Foo {
    void printName();

    // 인터페이스에 나중에 공통적인 기능 추가 케이스 발생
    // default 메소드로 지정해 주어, 컴파일 에러가 발생하지 않고 기능 추가 가능
    // Collection.removeIf 메소드 참조
    // 특정 구현체가 null을 return 하면 default 메소드가 제대로 동작하지 않을 수 있다.
    // 이런 케이스를 대비하여 반드시 문서화 할것 (@implSpec 자바독 태그 사용)
    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    };
    String getName();

    // 인터페이스 추상메소드로 object 메소드를 선언하는 것은 가능하다.
    // 선언하는 의미는 기본 object와 다르게 사용하겠다 할때 선언. 문서화 해야한다.
    String toString();

    // toString 은 Object의 기본 메소드 이므로 default로 재정의 할 수 없다.
//    default String toString(){
//    }

    // 헬퍼 또는 유틸리티 메소드를 제공할 때 인터페이스에 스태틱 메소드를 선언 한다.
    static void printAnything(){
        System.out.println("anything");
    }
}
