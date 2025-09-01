package com.twentyfour.coupang.coding.r2;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

enum SlotType{
    NA,
    BIKE,
    CAR,
    TRUCK;
}

enum VehicleType{
    BIKE,
    CAR,
    TRUCK;
}

@Data
class Slot{
    private int id;
    private SlotType slotType;

    public Slot(int id, SlotType slotType) {
        this.id = id;
        this.slotType = slotType;
    }
}

@Data
abstract class Vehicle{
    private String numberPlate;
    private VehicleType type;

    public Vehicle(String numberPlate, VehicleType type) {
        this.numberPlate = numberPlate;
        this.type = type;
    }
}

class Bike extends Vehicle{
    public Bike(String numberPlate, VehicleType type) {
        super(numberPlate, type);
    }
}

class Car extends Vehicle{
    public Car(String numberPlate, VehicleType type) {
        super(numberPlate, type);
    }
}

class Truck extends Vehicle{
    public Truck(String numberPlate, VehicleType type) {
        super(numberPlate, type);
    }
}

@Data
class Floor{

    private static int nextId;
    private int id;

    private int bikeSlotCount;
    private int carSlotCount;

    private int truckSlotCount;
    PriorityQueue<Slot> bikeSlots;
    PriorityQueue<Slot> carSlots;
    PriorityQueue<Slot> truckSlots;

    Comparator<Slot> comp = Comparator.comparingInt(Slot::getId);

    Floor(int bikeSlotCount, int carSlotCount, int truckSlotCount){
        this.id = nextId++;
        this.bikeSlotCount = bikeSlotCount;
        this.carSlotCount = carSlotCount;
        this.truckSlotCount = truckSlotCount;
        this.bikeSlots = new PriorityQueue<>(comp);
        populate(bikeSlots, bikeSlotCount, SlotType.BIKE);
        this.carSlots = new PriorityQueue<>(comp);
        populate(carSlots, carSlotCount, SlotType.CAR);
        this.truckSlots = new PriorityQueue<>(comp);
        populate(truckSlots, truckSlotCount, SlotType.TRUCK);
    }

    public void populate(PriorityQueue<Slot> pq, int count, SlotType slotType){
        for(int i=1; i<=count; i++){
            pq.add(new Slot(i, slotType));
        }
    }

    public boolean isSlotTypeAvailable(SlotType slotType){
        switch (slotType){
            case CAR -> {
                return !carSlots.isEmpty();
            }
            case BIKE -> {
                return !bikeSlots.isEmpty();
            }
            case TRUCK ->
            {
                return !truckSlots.isEmpty();
            }
        }
        return false;
    }

    public Slot getSlot(Vehicle vehicle) throws Exception {
            switch (vehicle.getType()) {
                case BIKE:
                    synchronized (bikeSlots){
                        if (!bikeSlots.isEmpty()) {
                            return bikeSlots.poll();
                        }
                    }
                    throw new Exception("no bike slots available");
                case CAR:
                    synchronized (carSlots){
                        if (!carSlots.isEmpty()) {
                            return carSlots.poll();
                        }
                    }
                    throw new Exception("no car slots available");
                case TRUCK:
                    synchronized (truckSlots) {
                        if (!truckSlots.isEmpty()) {
                            return truckSlots.poll();
                        }
                    }
                    throw new Exception("no truck slots available");
            }
            return null;
    }

    public void freeSlot(Ticket ticket){
        VehicleType type = ticket.getVehicle().getType();
        switch (type){
            case BIKE :
                bikeSlots.add(ticket.getGivenSlot());
            case CAR:
                carSlots.add(ticket.getGivenSlot());

            case TRUCK:
                truckSlots.add(ticket.getGivenSlot());
        }
    }
}


@Data
class Ticket{

    private static int nextId = 1;
    private int id;
    private Vehicle vehicle;

    private int floor;
    private Slot givenSlot;

    public Ticket(Vehicle vehicle) {
        this.id = nextId++;
        this.vehicle = vehicle;
//        this.givenSlot = givenSlot;
    }
}

class ParkingLot{
    private int id;
    List<Floor> floors;

    public ParkingLot(int id, List<Floor> floors) {
        this.id = id;
        this.floors = floors;
    }

    public Ticket park(Vehicle vehicle) throws Exception {
        SlotType slotType = getSlotType(vehicle.getType());
        Ticket ticket = new Ticket(vehicle);
        for(Floor curr : floors){
            if(curr.isSlotTypeAvailable(slotType)){
                Slot slot = curr.getSlot(vehicle);
                ticket.setGivenSlot(slot);
                ticket.setFloor(curr.getId());
                return ticket;
            }
        }
        throw new Exception("could not park");
    }

    private SlotType getSlotType(VehicleType vehicleType){
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

    public void freeSlot(Ticket ticket){
        int floorNo = ticket.getFloor();
        this.floors.get(floorNo).freeSlot(ticket);
    }
}
public class ParkingLotService {

    public static void main(String[] args) throws Exception {

        ParkingLotService parkingLotService = new ParkingLotService();

        Floor floor1 = new Floor(2, 3, 4);
        Floor floor2 = new Floor(2, 3, 4);
//        Floor floor3 = new Floor(2, 3, 4);

        List<Floor> floors = Arrays.asList(floor1, floor2);
        ParkingLot parkingLot = new ParkingLot(1, floors);

        Vehicle bike1 = new Bike("bike1", VehicleType.BIKE);
        Vehicle bike2 = new Bike("bike2", VehicleType.BIKE);
        Vehicle bike3 = new Bike("bike3", VehicleType.BIKE);
        Vehicle bike4 = new Bike("bike3", VehicleType.BIKE);

        Vehicle bike5 = new Bike("bike3", VehicleType.BIKE);
//        Vehicle bike3 = new Bike("bike3", VehicleType.BIKE);

        System.out.println(parkingLot.park(bike1));
        System.out.println(parkingLot.park(bike2));
        System.out.println();
        Ticket ticket = parkingLot.park(bike3);
        System.out.println(parkingLot.park(bike4));
        parkingLot.freeSlot(ticket);

        System.out.println(parkingLot.park(bike5));
    }


}
