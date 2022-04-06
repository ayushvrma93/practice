package com.flipkart.machinecoding.test;

import com.flipkart.machinecoding.BusinessException;
import com.flipkart.machinecoding.DateUtils;
import com.flipkart.machinecoding.ICenterManager;
import com.flipkart.machinecoding.IUserManager;
import com.flipkart.machinecoding.entity.Slot;
import com.flipkart.machinecoding.entity.User;
import com.flipkart.machinecoding.entity.enums.City;
import com.flipkart.machinecoding.entity.enums.Day;
import com.flipkart.machinecoding.entity.enums.WorkOutType;
import com.flipkart.machinecoding.impl.CenterManagerImpl;
import com.flipkart.machinecoding.impl.UserManagerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FlipFitDriver {


    private static ICenterManager centerManager = CenterManagerImpl.getInstance();
    private static IUserManager userManager = UserManagerImpl.getInstance();

    private void generalTesting() throws Exception {

        List<Day> daysClosed = new ArrayList<>();
        daysClosed.add(Day.MONDAY);
        daysClosed.add(Day.WEDNESDAY);

        System.out.println("Creating Marathalli center:");
        System.out.println(centerManager.add("Marathalli", City.BANGALORE, daysClosed, 10));

        System.out.println("Creating Belandur center:");
        System.out.println(centerManager.add("Belandur", City.BANGALORE, daysClosed, 10));

        System.out.println("Adding workout type(weights):");
        System.out.println(centerManager.addWorkoutType("Marathalli", WorkOutType.WEIGHTS));

        System.out.println("Adding workout type(swimming):");
        System.out.println(centerManager.addWorkoutType("Marathalli", WorkOutType.SWIMMING));

        System.out.println("Adding slot(weights):");
        List<WorkOutType> workOutTypes = new ArrayList<>();

        workOutTypes.add(WorkOutType.WEIGHTS);
        workOutTypes.add(WorkOutType.SWIMMING);

        List<Integer> capacities = new ArrayList<>();
        capacities.add(10);
        capacities.add(1);
        System.out.println(centerManager.addSlots(DateUtils.parse("2022-04-07"), "Marathalli", workOutTypes, "700", capacities));

        System.out.println("Registering user:");
        System.out.println(userManager.register("Ayush", 28, City.BANGALORE));

        System.out.println("Registering user:");
        System.out.println(userManager.register("Aman", 27, City.BANGALORE));

        System.out.println("Getting available slots for Marathalli:");
        List<Slot> availableSlots = userManager.getAvailableSlots("Marathalli", DateUtils.parse("2022-04-07"));

        if(availableSlots.isEmpty()){ System.out.println("No slots available");}
        else {System.out.println(availableSlots);}

        System.out.println("Booking weights seat for user Ayush:");
        System.out.println(userManager.book("Marathalli", 1, 1, WorkOutType.WEIGHTS));

//        System.out.println("Booking weights seat for user Aman:");
//        System.out.println(userManager.book("Marathalli", 2, 1, WorkOutType.WEIGHTS));

//        System.out.println("Booking swimming seat for user:");
//        System.out.println(userManager.book("Marathalli", 1, 2, WorkOutType.SWIMMING));

        System.out.println("User booking: ");
        System.out.println(userManager.viewUserBooking(1, DateUtils.parse("2022-04-07")));

        System.out.println("View User booking: ");
        System.out.println(userManager.viewUserBooking(1, DateUtils.parse("2022-04-07")));

        System.out.println("Cancel booking: ");
        System.out.println(userManager.cancelSlot("Marathalli", 1, 1, WorkOutType.WEIGHTS));

        System.out.println("View User booking after cancellation: ");
        System.out.println(userManager.viewUserBooking(1, DateUtils.parse("2022-04-07")));

        System.out.println("Booking weights seat for user Aman: ");
        System.out.println(userManager.book("Marathalli", 2, 1, WorkOutType.WEIGHTS));
    }

    private void loadTesting() throws Exception {

//        List<Day> daysClosed = new ArrayList<>();
//        daysClosed.add(Day.MONDAY);
//        daysClosed.add(Day.WEDNESDAY);
//
//        System.out.println("Creating Marathalli center:");
//        System.out.println(centerManager.add("Marathalli", City.BANGALORE, daysClosed, 100));
//
//
//
//        System.out.println("Adding workout type(weights):");
//        System.out.println(centerManager.addWorkoutType("Marathalli", WorkOutType.WEIGHTS));
//
//        System.out.println("Adding slot(weights):");
//        List<WorkOutType> workOutTypes = new ArrayList<>();
//
//        workOutTypes.add(WorkOutType.WEIGHTS);
//
//        List<Integer> capacities = new ArrayList<>();
//        capacities.add(10);
//        //capacities.add(1);
//        System.out.println(centerManager.addSlots(DateUtils.parse("2022-04-07"), "Marathalli", workOutTypes, "700", capacities));
        List<User> users = createNUsers(100);
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for(User user : users){
            Runnable worker = new BookingRunnable(user);
            executorService.execute(worker);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {}
        System.out.println("Finished all threads");
    }

    private List<User> createNUsers(int n){

        List<User> users = new ArrayList<>();

        for(int i=0; i<n; i++){
            String username = "Ayush" + i;
            User user = null;
            try {
                user = userManager.register(username, 28, City.BANGALORE);
            } catch (BusinessException e) {
                System.out.println("can't register user " + user.getName());
            }
            users.add(user);
        }
        return users;
    }

    public static void main(String[] args) throws Exception {

        FlipFitDriver flipFitDriver = new FlipFitDriver();
        flipFitDriver.generalTesting();
        flipFitDriver.loadTesting();
    }
}
