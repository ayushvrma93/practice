package com.twentytwo.flipkart.machinecoding.impl;

import com.twentytwo.flipkart.machinecoding.BusinessException;
import com.twentytwo.flipkart.machinecoding.DataAccessorFactory;
import com.twentytwo.flipkart.machinecoding.IDataAccessor;
import com.twentytwo.flipkart.machinecoding.IUserManager;
import com.twentytwo.flipkart.machinecoding.entity.Booking;
import com.twentytwo.flipkart.machinecoding.entity.Center;
import com.twentytwo.flipkart.machinecoding.entity.Slot;
import com.twentytwo.flipkart.machinecoding.entity.User;
import com.twentytwo.flipkart.machinecoding.entity.enums.City;
import com.twentytwo.flipkart.machinecoding.entity.enums.WorkOutType;

import java.util.Date;
import java.util.List;

import static com.twentytwo.flipkart.machinecoding.Constants.WorkingDB.NAME;

public class UserManagerImpl implements IUserManager {

    private static final IUserManager INSTANCE = new UserManagerImpl();
    private static DataAccessorFactory dataAccessorFactory;
    private static IDataAccessor dataAccessor;

    public static IUserManager getInstance() {
        return INSTANCE;
    }

    public UserManagerImpl(){
        dataAccessorFactory = DataAccessorFactory.getInstance();
        dataAccessor = dataAccessorFactory.getDataAccessor(NAME);
    }

    @Override
    public User register(String name, int age, City city) throws BusinessException {
        User newUser = new User(name, age, city);
        int id = dataAccessor.register(newUser);
        return dataAccessor.getUser(id);
    }

    @Override
    public List<Slot> getAvailableSlots(String centerName, Date date) throws BusinessException {
        Center existingCenter = dataAccessor.getCenter(centerName);
        return existingCenter.getAvailableSlot(date);
    }

    @Override
    public Booking book(String centerName, int userId, int slotId, WorkOutType workOutType) throws BusinessException {

        Center existingCenter = dataAccessor.getCenter(centerName);

        if(!existingCenter.isWorkOutTypeSupported(workOutType)){
            System.out.println("This workout type is not supported at this center");
            throw new BusinessException("Invalid Workout type for the center");
        }

        Booking booking = existingCenter.book(userId, slotId, workOutType);

        if(booking == null){
            System.out.println("Workout is full, seat not available");
            throw new BusinessException("No seats available for this workout.");
        }

        User user = dataAccessor.getUser(userId);
        dataAccessor.updateUserBookings(existingCenter, user, booking);
        return booking;
    }

    @Override
    public List<Booking> viewUserBooking(int userId, Date date) {
        List<Booking> bookingsForUser = dataAccessor.viewUserBooking(userId, date);
        return bookingsForUser;
    }

    @Override
    public Booking cancelSlot(String centerName, int userId, int slotId, WorkOutType workOutType) throws BusinessException {

        Center existingCenter = dataAccessor.getCenter(centerName);
        Booking cancelledBooking = existingCenter.cancelSlot(userId, slotId, workOutType);
        dataAccessor.cancelSlot(cancelledBooking);
        return cancelledBooking;
    }
}
