package com.flipkart.machinecoding.impl;

import com.flipkart.machinecoding.BusinessException;
import com.flipkart.machinecoding.DataAccessorFactory;
import com.flipkart.machinecoding.ICenterManager;
import com.flipkart.machinecoding.IDataAccessor;
import com.flipkart.machinecoding.entity.Center;
import com.flipkart.machinecoding.entity.enums.City;
import com.flipkart.machinecoding.entity.enums.Day;
import com.flipkart.machinecoding.entity.enums.WorkOutType;

import java.util.Date;
import java.util.List;

import static com.flipkart.machinecoding.Constants.Misc.ONE_HOUR;
import static com.flipkart.machinecoding.Constants.WorkingDB.NAME;

public class CenterManagerImpl implements ICenterManager {

    private static final ICenterManager INSTANCE = new CenterManagerImpl();
    private static DataAccessorFactory dataAccessorFactory;
    private static IDataAccessor dataAccessor;

    public CenterManagerImpl(){
        dataAccessorFactory = DataAccessorFactory.getInstance();
        dataAccessor = dataAccessorFactory.getDataAccessor(NAME);
    }

    public static ICenterManager getInstance() {
        return INSTANCE;
    }

    @Override
    public Center add(String centerName, City city, List<Day> daysClosed, int slotCount) throws Exception {

        Center newCenter = new Center(centerName, city, daysClosed, slotCount);
        int savedId = dataAccessor.saveCenter(newCenter);
        return dataAccessor.getCenter(centerName);
    }

    @Override
    public Center addWorkoutType(String centerName, WorkOutType workOutType) throws BusinessException {

        Center existingCenter = dataAccessor.getCenter(centerName);

        if(existingCenter == null){
            System.out.println("Center does not exist");
            throw new BusinessException("Invalid center name");
        }
        String existingCenterName = dataAccessor.addWorkoutType(existingCenter, workOutType);

        return dataAccessor.getCenter(existingCenterName);
    }

    @Override
    public Center addSlots(Date date, String centerName, List<WorkOutType> workOutTypes, String startTime, List<Integer> seatCapacities) throws BusinessException {

        if(!isValid(workOutTypes, seatCapacities)){
            System.out.println("Wrong input, all workout types must have seat capacities");
            throw new BusinessException("Invalid input");
        }

        Center existingCenter = dataAccessor.getCenter(centerName);

        if(!existingCenter.areWorkOutTypesSupported(workOutTypes)){
            System.out.println("This workout type is not supported at this center");
            throw new BusinessException("Invalid Workout type for the center");
        }

        String existingCenterName = dataAccessor.addSlots(existingCenter, date, workOutTypes, startTime, String.valueOf(Integer.valueOf(startTime) + ONE_HOUR), seatCapacities);
        return dataAccessor.getCenter(existingCenterName);
    }

    private boolean isValid(List<WorkOutType> workOutTypes, List<Integer> seatCapacities) {
        return workOutTypes.size() == seatCapacities.size();
    }
}
