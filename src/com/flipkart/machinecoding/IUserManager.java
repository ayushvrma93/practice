package com.flipkart.machinecoding;

import com.flipkart.machinecoding.entity.Booking;
import com.flipkart.machinecoding.entity.Slot;
import com.flipkart.machinecoding.entity.User;
import com.flipkart.machinecoding.entity.enums.City;
import com.flipkart.machinecoding.entity.enums.WorkOutType;

import java.util.Date;
import java.util.List;

public interface IUserManager {

    User register(String name, int age, City city) throws BusinessException;
    List<Slot> getAvailableSlots(String centerName, Date date) throws BusinessException;
    Booking book(String centerName, int userId, int slotId, WorkOutType workOutType) throws BusinessException;
    List<Booking> viewUserBooking(int userId, Date date);
    Booking cancelSlot(String centerName, int userId, int slotId, WorkOutType workOutType) throws BusinessException;
}
