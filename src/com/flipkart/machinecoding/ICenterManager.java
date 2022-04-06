package com.flipkart.machinecoding;

import com.flipkart.machinecoding.entity.Center;
import com.flipkart.machinecoding.entity.enums.City;
import com.flipkart.machinecoding.entity.enums.Day;
import com.flipkart.machinecoding.entity.enums.WorkOutType;

import java.util.Date;
import java.util.List;

public interface ICenterManager {

    Center add(String centerName, City city, List<Day> daysClosed, int slotCount) throws Exception;
    Center addWorkoutType(String centerName, WorkOutType workOutType) throws BusinessException;
    Center addSlots(Date date, String centerName, List<WorkOutType> workOutTypes, String startTime, List<Integer> seatCapacities) throws BusinessException;
}
