package org.example.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyTime {
    private LocalTime time;

    public MyTime(LocalTime time) {
        this.time = time;
    }

    public static MyTime now() {
        return new MyTime(LocalTime.now());
    }

    @Override
    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
