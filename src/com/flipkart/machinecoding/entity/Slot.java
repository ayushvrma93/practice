package com.flipkart.machinecoding.entity;

import com.flipkart.machinecoding.BusinessException;
import com.flipkart.machinecoding.entity.enums.BookingStatus;
import com.flipkart.machinecoding.entity.enums.WorkOutType;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class Slot {

    private int id;
    private String startTime;
    private String endTime;

    private Map<WorkOutType, Integer> workOutTypeAndSeatCapacity = new HashMap<>();
    private Map<WorkOutType, AtomicInteger> workOutTypeAndBookedSeatCount = new HashMap<>();
    private Map<WorkOutType, Set<Integer>> workOutTypeAndAllottedUser = new HashMap<>();
    //private Map<WorkOutType, Queue<Integer>> workOutTypeAndWaitListUsers = new HashMap<>();


    public Slot(int id, String startTime, String endTime, List<WorkOutType> workOutTypes, List<Integer> seatCapacities) {

        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;

        //not checking if this already exists, as it is stated in the question
        for(int i = 0; i < workOutTypes.size(); i++){
            workOutTypeAndSeatCapacity.put(workOutTypes.get(i), seatCapacities.get(i));
        }
    }

    public boolean isAvailable(){

        for(var entry : workOutTypeAndSeatCapacity.entrySet()) {

            WorkOutType currWorkOutType = entry.getKey();

            AtomicInteger bookedSeats = workOutTypeAndBookedSeatCount.get(currWorkOutType);

            if(bookedSeats == null || entry.getValue() > bookedSeats.get()) return true;

        }
        return false;
    }


    public boolean isWorkOutAvailable(WorkOutType workOutType){

        Integer seatCapacity = workOutTypeAndSeatCapacity.get(workOutType);
        AtomicInteger bookedSeat = workOutTypeAndBookedSeatCount.get(workOutType);

        if(bookedSeat == null) {
            bookedSeat = new AtomicInteger();
            bookedSeat.set(0);
        }
        return seatCapacity > bookedSeat.get();
    }

    public Booking book(int userId, WorkOutType workOutType) throws BusinessException {

        if(hasUserAlreadyBooked(userId)){
            System.out.println("User has already booked a workout in this slot");
            throw new BusinessException("User has already booked a workout in this slot");
        }

        boolean isSeatBooked = bookSeat(workOutType);

        if(!isSeatBooked) {
            /*
                checkAndAddInWaitList()
             */
            System.out.println("No seat available.");
            throw new BusinessException("No seat available.");
        }
        updateBookedUsers(userId, workOutType);
        return new Booking(userId, this.id, workOutType, BookingStatus.BOOKED);
    }


    public Booking cancel(int userId, WorkOutType workOutType) throws BusinessException {

        Set bookedUsers = workOutTypeAndAllottedUser.get(workOutType);

        if(!bookedUsers.contains(userId)){
            System.out.println("User has not booked a seat yet");
            throw new BusinessException("User has not booked a seat yet");
        }

        cancelSeat(workOutType);
        updateCancelledUser(userId, workOutType);
        return new Booking(userId, this.id, workOutType, BookingStatus.CANCELLED);
    }


    private boolean bookSeat(WorkOutType workOutType){

        Integer seatCapacity = workOutTypeAndSeatCapacity.get(workOutType);
        AtomicInteger bookedSeat = workOutTypeAndBookedSeatCount.get(workOutType);

        if(bookedSeat == null) {
            bookedSeat = new AtomicInteger();
            bookedSeat.set(0);
        }

        if(bookedSeat.addAndGet(1) <= seatCapacity){
            workOutTypeAndBookedSeatCount.put(workOutType, bookedSeat);
            return true;
        }

        bookedSeat.addAndGet(-1); return false;
    }

    private void updateBookedUsers(int userId, WorkOutType workOutType){

        Set bookedUsers = workOutTypeAndAllottedUser.get(workOutType);

        if(bookedUsers == null) {
            bookedUsers = new HashSet();
        }
        bookedUsers.add(userId);
        workOutTypeAndAllottedUser.put(workOutType, bookedUsers);
    }

    private void cancelSeat(WorkOutType workOutType){
        AtomicInteger bookedSeat = workOutTypeAndBookedSeatCount.get(workOutType);
        /*
            addIfUserIsWaiting()
         */
        bookedSeat.addAndGet(-1);
    }

    private void updateCancelledUser(int userId, WorkOutType workOutType){
        Set bookedUsers = workOutTypeAndAllottedUser.get(workOutType);
        bookedUsers.remove(userId);
        workOutTypeAndAllottedUser.put(workOutType, bookedUsers);
    }

    private boolean hasUserAlreadyBooked(int userId){
        for(var entry : workOutTypeAndAllottedUser.entrySet()){
            Set<Integer> allottedUserForWorkoutType = entry.getValue();
            if(allottedUserForWorkoutType.contains(userId)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", workOutTypeAndSeatCapacity=" + workOutTypeAndSeatCapacity +
                ", workOutTypeAndBookedSeatCount=" + workOutTypeAndBookedSeatCount +
                ", workOutTypeAndAllottedUser=" + workOutTypeAndAllottedUser +
                '}';
    }
}
