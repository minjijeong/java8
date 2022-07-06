package me.whiteship.java8to11.Date와TimeAPI;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Date와TimeAPI {
    public static void main(String[] args) throws InterruptedException {
        // 과거에 힘들게 여러개로 씀..
        // Date -> date, time 모두 포함... 작명센스가 이상함
        Date date = new Date();
        // 이폭타임을 출력... 실제 시간이 아님..
        long time = date.getTime();

        // 3초 동안 쓰레드로 재우고 다시 예전 시간으로 set 하면 다시 돌아감^^
        // 객체 상태를 바꿀수 있기 때문에 mutable
        // 멀티쓰레드 환경에서 안전하게 쓸수가 없다.
        Thread.sleep(1000 * 3);
        Date after3Second = new Date();
        System.out.println(after3Second);
        // Mon Jul 04 01:13:32 KST 2022
        after3Second.setTime(time);
        System.out.println(after3Second);
        // Mon Jul 04 01:13:29 KST 2022

        // Month를 숫자로 받으면.. 혼돈이 올수 있어 constant value로 수정해야 한다.
        Calendar mybirth = new GregorianCalendar(1988, Calendar.NOVEMBER,13);
        SimpleDateFormat dateFormat = new SimpleDateFormat();


    }
}
