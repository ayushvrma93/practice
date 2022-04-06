package com.flipkart.machinecoding.entity.enums;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toMap;

public enum Day {

    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);


    private int value;

    public final static Map<Integer, Day> dayAndNo = stream(Day.values()).collect(toMap(day -> day.value, day -> day));

    Day(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static Day valueOf(int dayOfWeek){
        return dayAndNo.get(dayOfWeek);
    }
}
