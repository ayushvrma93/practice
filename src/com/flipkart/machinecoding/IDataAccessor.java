package com.flipkart.machinecoding;

import com.flipkart.machinecoding.entity.Booking;
import com.flipkart.machinecoding.entity.Center;
import com.flipkart.machinecoding.entity.Slot;
import com.flipkart.machinecoding.entity.User;
import com.flipkart.machinecoding.entity.enums.WorkOutType;

import java.util.Date;
import java.util.List;

public interface IDataAccessor {

    int saveCenter(Center center) throws Exception;
    Center getCenter(String centerName) throws BusinessException;
    String addWorkoutType(Center existingCenter, WorkOutType workOutType) throws BusinessException;
    String addSlots(Center existingCenter, Date date, List<WorkOutType> workOutTypes, String startTime, String endTime, List<Integer> seatCapacities) throws BusinessException;

    int register(User user);
    User getUser(int userId) throws BusinessException;
    List<Booking> viewUserBooking(int userId, Date date);
    Booking cancelSlot(Booking cancelledBooking) throws BusinessException;
    void updateUserBookings(Center center, User user, Booking booking);
}
