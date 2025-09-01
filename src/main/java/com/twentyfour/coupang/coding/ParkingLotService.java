package com.twentyfour.coupang.coding;

import com.twentyfour.coupang.machinecoding.movieticket.exception.CustomException;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

enum SlotType{
    NA(0),
    BIKE(1),
    CAR(2),
    TRUCK(3);

    SlotType(int slotType){}

    static SlotType getSlotTypeForVehicleType(VehicleType vehicleType){
        switch (vehicleType){
            case BIKE -> {
                return SlotType.BIKE;
            }
            case CAR -> {
                return SlotType.CAR;
            }

            case TRUCK -> {
                return SlotType.TRUCK;
            }
        }
        return SlotType.NA;
    }
}

enum VehicleType{
    BIKE("BIKE"),
    CAR("CAR"),
    TRUCK("TRUCK");

    VehicleType(String vehicleType){}
}

@Data
class Slot{
    private int id;
    private SlotType slotType;
    private int floor;

}

@Data
abstract class Vehicle{
    private String numberPlate;
    private int capacity;

    private VehicleType type;

    public Vehicle(String numberPlate, int capacity, VehicleType type) {
        this.numberPlate = numberPlate;
        this.capacity = capacity;
        this.type = type;
    }
}

class Bike extends Vehicle{
    public Bike(String numberPlate, int capacity, VehicleType type) {
        super(numberPlate, capacity, type);
    }
}

class Car extends Vehicle{
    public Car(String numberPlate, int capacity, VehicleType type) {
        super(numberPlate, capacity, type);
    }
}

class Truck extends Vehicle{
    public Truck(String numberPlate, int capacity, VehicleType type) {
        super(numberPlate, capacity, type);
    }
}

@Data
class Floor{
    private static int nextId = 1;
    private int id;
    private final int bikeSlotCount;
    private final int carSlotCount;

    private final int truckSlotCount;

    private PriorityQueue<Integer> bikeSlots;
    private PriorityQueue<Integer> carSlots;
    private PriorityQueue<Integer> truckSlots;

    public Floor(int bikeSlotCount, int carSlotCount, int truckSlotCount) {
        this.id = nextId++;
        this.bikeSlotCount = bikeSlotCount;
        this.carSlotCount = carSlotCount;
        this.truckSlotCount = truckSlotCount;
        this.bikeSlots = new PriorityQueue<>(bikeSlotCount);
        populateSlots(bikeSlots, bikeSlotCount);
        this.carSlots = new PriorityQueue<>(carSlotCount);
        populateSlots(carSlots, carSlotCount);
        this.truckSlots = new PriorityQueue<>(truckSlotCount);
        populateSlots(truckSlots, truckSlotCount);
    }

    public boolean areSlotsFull(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:
                return bikeSlots.isEmpty();

            case CAR:
                return carSlots.isEmpty();

            case TRUCK:
                return truckSlots.isEmpty();
        }
        return false;
    }

    public Integer getSlot(Vehicle vehicle) throws CustomException {
        VehicleType vehicleType = vehicle.getType();

        switch (vehicleType){
            case BIKE:
                if(bikeSlots.size() == 0){
                    throw new CustomException("no spots available for bikes");
                } else return bikeSlots.poll();

            case CAR:
                if(carSlots.size() == 0){
                    throw new CustomException("no spots available for cars");
                } else return carSlots.poll();

            case TRUCK:
                if(truckSlots.size() == 0){
                    throw new CustomException("no spots available for trucks");
                } else return truckSlots.poll();
        }
        return -1;
    }

    public void freeSlot(int slotId, SlotType slotType){
        switch (slotType){
            case BIKE:
                bikeSlots.add(slotId);
                return;

            case CAR:
                carSlots.add(slotId);
                return;

            case TRUCK:
                truckSlots.add(slotId);
        }
    }

    private void populateSlots(PriorityQueue<Integer> pq, Integer slotCount){
        for(int i=1; i<=slotCount; i++){
            pq.add(i);
        }
    }
}


@Data
class ParkingTicket{

    private static int id = 1;
    int floorId;
    int slot;
    Vehicle vehicle;

    public ParkingTicket(int floorId, int slot, Vehicle vehicle) {
        this.floorId = floorId;
        this.slot = slot;
        this.vehicle = vehicle;
    }
}

class ParkingLot {

    int floorCount;
    List<Floor> floors;

    ParkingLot(List<Floor> floors) throws CustomException {
        this.floorCount = floors.size();
        this.floors = floors;
    }

    public ParkingTicket park(Vehicle vehicle) throws CustomException {
        Floor parkingFloor = null;
        int parkingSlot = 0;
        for(Floor currFloor : floors) {
            if (!currFloor.areSlotsFull(vehicle.getType())) {
                parkingSlot = currFloor.getSlot(vehicle);
                parkingFloor = currFloor;
                break;
            }
        }
        if(parkingFloor == null) {
            throw new CustomException("no parking slots available for the vehicle type");
        }
        ParkingTicket ticket = new ParkingTicket(parkingFloor.getId(), parkingSlot, vehicle);

        return ticket;
    }

//    public void freeSlot(ParkingTicket ticket){
//        Floor floor = this.floors.get(ticket.getFloorId()-1);
//        int slotId = ticket.getSlot();
//        floor.freeSlot(slotId, SlotType.getSlotTypeForVehicleType(ticket.getVehicle().getType()));
//    }

}

public class ParkingLotService{
    public static void main(String[] args) throws CustomException {
        Floor floorG = new Floor(1, 5, 3);
        Floor floorF = new Floor(10, 5, 3);
        Floor floorS = new Floor(20, 15, 12);

        List<Floor> parkingOneFloors = Arrays.asList(floorG, floorF, floorS);
        ParkingLot parkingLot = null;

        try {
            parkingLot = new ParkingLot(parkingOneFloors);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }

        Vehicle babyTiger = new Bike("KA 05 LM 0718", 2, VehicleType.BIKE);
        Vehicle thunderbird = new Bike("WB 08B 1676", 2, VehicleType.BIKE);

        Vehicle thar = new Car("KA 03 NH 7322", 4, VehicleType.CAR);

        ParkingTicket parkingTicket1 = parkingLot.park(babyTiger);
        System.out.println(parkingTicket1);

        System.out.println(parkingLot.park(thunderbird));

        ParkingTicket tharTicket = parkingLot.park(thar);
        System.out.println(tharTicket);
    }
}

