package com.twentyfour.dream11.machinecoding.jobscheduler.service;

import com.twentyfour.dream11.machinecoding.jobscheduler.entities.Job;

import java.time.Instant;

public class JobHelper {

    public synchronized static Job executeAndSchedule(Job job){
        if (Instant.now().isAfter(job.getNextExecution()) || Instant.now().equals(job.getNextExecution())) {
            System.out.println(job.getJobString());
        }
        Instant nextExecutionInstant = Instant.now().plus(job.getDelay());
        job.setNextExecution(nextExecutionInstant);
        return job;
    }
}
