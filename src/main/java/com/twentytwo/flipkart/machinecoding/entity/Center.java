package com.twentytwo.flipkart.machinecoding.entity;

import com.twentytwo.flipkart.machinecoding.BusinessException;
import com.twentytwo.flipkart.machinecoding.DateUtils;
import com.twentytwo.flipkart.machinecoding.entity.enums.City;
import com.twentytwo.flipkart.machinecoding.entity.enums.Day;
import com.twentytwo.flipkart.machinecoding.entity.enums.WorkOutType;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Center {

    private int id;
    private String name;
    private City cty;
    private List<Day> daysClosed;
    private List<WorkOutType> supportedWorkouts = new ArrayList<>();
    private int slotCount;

    private static int currId = 1;
    private static int slotId = 1;

    //Assumption: Admin has to add slots for each day
    private Map<Date, List<Slot>> dateAndSlots = new HashMap<>();


    public Center(String name, City cty, List<Day> daysClosed, int slotCount) {
        this.id = currId++;
        this.name = name;
        this.cty = cty;
        this.daysClosed = daysClosed;
        this.slotCount = slotCount;
    }

    public Center addWorkOutType(WorkOutType workOutType){
        this.supportedWorkouts.add(workOutType);
        return this;
    }

    public Center addSlot(Date date, List<WorkOutType> workOutTypes, String startTime, String endTime, List<Integer> seatCapacities) throws BusinessException {

        Day day = DateUtils.toDayOfWeek(date);

        if(!isSlotValid(day)){
            System.out.println("Invalid day of the week to add slot");
            throw new BusinessException("Invalid day of the week.");
        }

        List<Slot> slots;


        Slot newSlot = new Slot(slotId++, startTime, endTime, workOutTypes, seatCapacities);

        if(dateAndSlots.containsKey(date)){

            slots = dateAndSlots.get(date);

            if(slots.size() == this.slotCount){
                System.out.println("Max no of slots allowed for current days have been reached. Can not add more slots");
                throw new BusinessException("Invalid date for adding slot");
            }
        } else {
            slots = new ArrayList<>();

        }
        slots.add(newSlot);
        this.dateAndSlots.put(date, slots);
        return this;
    }


    public List<Slot> getAvailableSlot(Date date){

        List<Slot> allSlots = dateAndSlots.get(date);
        List<Slot> availableSlots = new ArrayList<>();

        for(Slot slot : allSlots){
            if(slot.isAvailable()){
                availableSlots.add(slot);
            }
        }
        return availableSlots;
    }

    public Booking book(int userId, int slotId, WorkOutType workOutType) throws BusinessException {

        Slot slot = getSlotById(slotId);
        Booking booked = null;

        if(slot == null){
            System.out.println("No such slot available");
            throw new BusinessException("Invalid slot id.");
        }

        if(slot.isWorkOutAvailable(workOutType)){
            booked = slot.book(userId, workOutType);
            booked.setCenterId(this.id);
            booked.setCenterName(this.name);
        }
        return booked;
    }

    public boolean isSlotValid(Day day){
        return daysClosed.stream().noneMatch(currDay -> currDay.equals(day));
    }

    private Slot getSlotById(int slotId){

        for(var entry : dateAndSlots.entrySet()){

            List<Slot> slots = entry.getValue();

            for(Slot currSlot : slots){
                if(slotId == currSlot.getId()) return currSlot;
            }
        }
        return null;
    }


    public Booking cancelSlot(int userId, int slotId, WorkOutType workOutType) throws BusinessException {

        Slot slot = getSlotById(slotId);
        Booking booked = null;

        if(slot == null){
            System.out.println("No such slot available");
            throw new BusinessException("Invalid slot id.");
        }

        Booking cancelledBooking = slot.cancel(userId, workOutType);
        cancelledBooking.setCenterId(this.id);
        cancelledBooking.setCenterName(this.name);
        return cancelledBooking;
    }

    public boolean areWorkOutTypesSupported(List<WorkOutType> workOutTypes){
        return getSupportedWorkouts().equals(workOutTypes);
    }

    public boolean isWorkOutTypeSupported(WorkOutType workOutType){
        return getSupportedWorkouts().stream().anyMatch(type -> type.equals(workOutType));
    }


    public Date getDateForSlot(int slotId){
        Slot slot = getSlotById(slotId);
        for(var entry : dateAndSlots.entrySet()){
            List<Slot> currDateSlots = entry.getValue();

            for(Slot currSlot : currDateSlots){
                if(currSlot.equals(slot))
                    return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Center{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cty=" + cty +
                ", daysClosed=" + daysClosed +
                ", supportedWorkouts=" + supportedWorkouts +
                ", slotCount=" + slotCount +
                ", dateAndSlots=" + dateAndSlots +
                '}';
    }
}
