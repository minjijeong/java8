package me.whiteship.java8to11.model;

import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public class OnlineClass {
    private Integer id;

    @Override
    public String toString() {
        return "OnlineClass{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", closed=" + closed +
                ", progress=" + progress +
                '}';
    }

    private String title;
    private boolean closed;

    private Progress progress;


    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {
        // null이면 retrun empty, 아니면 of를 리턴
        return Optional.ofNullable(progress);
    }

//    // nullpoint exception 발생
//    // null check를 더블로 해야하므로 파라미터로 Optional은 안받는 것이 좋다
//    public void setProgress(Optional<Progress> progress) {
//        if(progress != null) {
//            progress.ifPresent(p -> this.progress = p);
//        }
//    }
}
