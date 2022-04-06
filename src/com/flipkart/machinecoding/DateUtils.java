package com.flipkart.machinecoding;

import com.flipkart.machinecoding.entity.enums.Day;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    public static final String ISO_DATE = "yyyy-MM-dd";

    public static Date parse(String date) throws ParseException {
            return new SimpleDateFormat(ISO_DATE).parse(date);
    }

    public static Day toDayOfWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return Day.valueOf(dayOfWeek);

    }

}
