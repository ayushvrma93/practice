package com.flipkart.machinecoding.impl;

import com.flipkart.machinecoding.BusinessException;
import com.flipkart.machinecoding.IDataAccessor;
import com.flipkart.machinecoding.entity.Booking;
import com.flipkart.machinecoding.entity.Center;
import com.flipkart.machinecoding.entity.User;
import com.flipkart.machinecoding.entity.enums.BookingStatus;
import com.flipkart.machinecoding.entity.enums.WorkOutType;

import java.util.*;

import static com.flipkart.machinecoding.Constants.Misc.ONE_HOUR;

public class InMemoryAccessor implements IDataAccessor {

    private static final IDataAccessor INSTANCE = new InMemoryAccessor();

    private Map<String, Center> nameAndCenter = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Map<Date, List<Booking>>> userDateAndBookings = new HashMap<>();

    public static IDataAccessor getInstance() {
        return INSTANCE;
    }

    @Override
    public int saveCenter(Center center) {
        nameAndCenter.put(center.getName(), center);
        return center.getId();
    }

    @Override
    public Center getCenter(String centerName) throws BusinessException {

        Center existingCenter = nameAndCenter.get(centerName);

        if(existingCenter == null){
            System.out.println("Center does not exist");
            throw new BusinessException("Invalid center name");
        }
        return nameAndCenter.get(centerName);
    }


    @Override
    public String addWorkoutType(Center existingCenter, WorkOutType workOutType) {
        existingCenter.addWorkOutType(workOutType);
        return existingCenter.getName();
    }

    @Override
    public String addSlots(Center existingCenter, Date date, List<WorkOutType> workOutTypes, String startTime, String endTime, List<Integer> seatCapacities) throws BusinessException {
        existingCenter.addSlot(date, workOutTypes, startTime, String.valueOf(Integer.valueOf(startTime) + ONE_HOUR), seatCapacities);
        return existingCenter.getName();
    }

    @Override
    public int register(User user) {
        users.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public User getUser(int userId) throws BusinessException {

        User user = users.get(userId);

        if(user == null){
            System.out.println("No such user exist");
            throw new BusinessException("Invalid user id provided: " + userId + ".");
        }
        return user;
    }

    @Override
    public List<Booking> viewUserBooking(int userId, Date date) {
        Map<Date, List<Booking>> dateAndBookings = userDateAndBookings.get(userId);
        List<Booking> bookingsForDate = dateAndBookings.get(date);
        return bookingsForDate;
    }

    @Override
    public Booking cancelSlot(Booking cancelledBooking) throws BusinessException {

        int userId = cancelledBooking.getUserId();
        Map<Date, List<Booking>> dateAndBookings = userDateAndBookings.get(userId);
        Center concernedCenter = getCenter(cancelledBooking.getCenterName());
        Date slotDate = concernedCenter.getDateForSlot(cancelledBooking.getSlotId());
        List<Booking> allBookings = dateAndBookings.get(slotDate);
        int index = allBookings.indexOf(cancelledBooking);
        Booking canBooking = allBookings.get(index);
        canBooking.setStatus(BookingStatus.CANCELLED);
        return cancelledBooking;
    }

    public void updateUserBookings(Center center, User user, Booking booking){

        int userId = booking.getUserId();
        Map<Date, List<Booking>> dateAndBookings;
        Date slotDate = center.getDateForSlot(booking.getSlotId());

        if(!userDateAndBookings.containsKey(userId)){
            dateAndBookings = new HashMap<>();
            List<Booking> bookings = new ArrayList<>();
            bookings.add(booking);
            dateAndBookings.put(slotDate, bookings);
        }

        else{
            dateAndBookings = userDateAndBookings.get(userId);

            List<Booking> bookings;
            if(!dateAndBookings.containsKey(slotDate)){
                bookings = new ArrayList<>();
            }
            else {
                bookings = dateAndBookings.get(slotDate);
            }
            bookings.add(booking);
            dateAndBookings.put(slotDate, bookings);
        }
        userDateAndBookings.put(userId, dateAndBookings);
    }
}
