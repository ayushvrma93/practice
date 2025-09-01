package com.twentyfour.dream11.machinecoding.jobscheduler;

import com.twentyfour.dream11.machinecoding.jobscheduler.entities.Job;
import com.twentyfour.dream11.machinecoding.jobscheduler.exceptions.CustomException;
import com.twentyfour.dream11.machinecoding.jobscheduler.service.JobSchedulingService;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class InMemoryJobScheduler {

    public static void main(String[] args) throws CustomException, InterruptedException {
        JobSchedulingService jobSchedulingService = new JobSchedulingService(100);
        Job job1 = new Job("test1", Duration.of(1, TimeUnit.SECONDS.toChronoUnit()));
        Job job2 = new Job("test2", Duration.of(5, TimeUnit.SECONDS.toChronoUnit()));
        jobSchedulingService.addJob(job1);
        jobSchedulingService.addJob(job2);

        while(true){
            jobSchedulingService.consumeJob();
            Thread.sleep(100);
        }
    }
}
