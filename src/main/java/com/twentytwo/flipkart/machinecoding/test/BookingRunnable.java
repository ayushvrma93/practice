package com.twentytwo.flipkart.machinecoding.test;

import com.twentytwo.flipkart.machinecoding.BusinessException;
import com.twentytwo.flipkart.machinecoding.ICenterManager;
import com.twentytwo.flipkart.machinecoding.IUserManager;
import com.twentytwo.flipkart.machinecoding.entity.Booking;
import com.twentytwo.flipkart.machinecoding.entity.User;
import com.twentytwo.flipkart.machinecoding.entity.enums.BookingStatus;
import com.twentytwo.flipkart.machinecoding.entity.enums.WorkOutType;
import com.twentytwo.flipkart.machinecoding.impl.CenterManagerImpl;
import com.twentytwo.flipkart.machinecoding.impl.UserManagerImpl;

public class BookingRunnable implements Runnable{

    private static ICenterManager centerManager = CenterManagerImpl.getInstance();
    private static IUserManager userManager = UserManagerImpl.getInstance();
    private User user;
    static int seatsBooked = 0;

    public BookingRunnable(User user) {
        this.user = user;
    }

    @Override
    public void run() {

        System.out.println("Booking weights seat for user: " + user.getName());
        Booking booking;
        try {
            booking = userManager.book("Marathalli", user.getId(), 1, WorkOutType.WEIGHTS);
        } catch (BusinessException e) {
            System.out.println("Seat not booked for "+ user.getId());
            return;
        }
        BookingStatus status = booking.getStatus();
        if(BookingStatus.BOOKED.equals(status)) { seatsBooked++; }
        System.out.println("Seats booked: " + seatsBooked + " user: " + user.getName());
    }
}
