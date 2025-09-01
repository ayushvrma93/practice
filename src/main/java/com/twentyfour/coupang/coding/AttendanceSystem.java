package com.twentyfour.coupang.coding;

import com.twentyfour.coupang.machinecoding.movieticket.exception.CustomException;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

@Data
class Student{
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
public class AttendanceSystem {

    Map<Student, NavigableMap<LocalDate, Boolean>> attendanceMap;

    AttendanceSystem(){
        attendanceMap = new HashMap<>();
    }

    public void addAttendanceForStudent(Student student, NavigableMap<LocalDate, Boolean> dateAttendanceMap){
        attendanceMap.put(student, dateAttendanceMap);
    }

    public Double calculateAttendanceBetweenRange(Student student, LocalDate startDate, LocalDate endDate) throws CustomException {
        if(!attendanceMap.containsKey(student)){
            throw new CustomException("invalid student id");
        }
        NavigableMap<LocalDate, Boolean> dateAttendanceMap = attendanceMap.get(student);

        NavigableMap<LocalDate, Boolean> submap = dateAttendanceMap.subMap(startDate, true, endDate, true);
        int presentCount = 0;

        for(Map.Entry<LocalDate, Boolean> entry : submap.entrySet()){
            if(entry.getValue().booleanValue() == true){
                presentCount++;
            }
        }

        return (double)presentCount/(double) submap.size() * 100;
    }

    public static void main(String[] args) throws CustomException {
        Student student1 = new Student(1, "A");
        AttendanceSystem attendanceSystem = new AttendanceSystem();

        NavigableMap<LocalDate, Boolean> student1Attendance = new TreeMap<>();

        LocalDate localDate1sept = LocalDate.of(2024, 9, 01);
        LocalDate localDate2sept = LocalDate.of(2024, 9, 02);
        LocalDate localDate3sept = LocalDate.of(2024, 9, 03);
        LocalDate localDate4sept = LocalDate.of(2024, 9, 04);
        LocalDate localDate5sept = LocalDate.of(2024, 9, 05);

        student1Attendance.put(localDate1sept, true);
        student1Attendance.put(localDate2sept, true);
        student1Attendance.put(localDate3sept, false);
        student1Attendance.put(localDate4sept, false);
        student1Attendance.put(localDate5sept, true);

        attendanceSystem.addAttendanceForStudent(student1, student1Attendance);
        System.out.println(attendanceSystem.calculateAttendanceBetweenRange(student1, localDate1sept, localDate5sept));

    }
}
