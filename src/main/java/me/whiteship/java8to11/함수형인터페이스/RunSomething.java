package me.whiteship.java8to11.함수형인터페이스;

/**
 * 인터페이스 : 명시적 선언의 역할
 * - 추상메소드 : 인터페이스의 추상메소드는 컴파일시 public abstract, 추상메소드로 변환
 * - 상수 : 인터페이스 내에 상수는 컴파일시 static final 변환
 */
@FunctionalInterface
public interface RunSomething {
    int number = 0;
    void doIt();
}
