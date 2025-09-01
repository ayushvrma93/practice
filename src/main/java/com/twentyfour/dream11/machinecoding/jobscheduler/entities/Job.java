package com.twentyfour.dream11.machinecoding.jobscheduler.entities;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;

@Data
public class Job {
    private String jobString;
    private Duration delay;

    private Instant nextExecution;

//    private LocalDateTime endTime;

    public Job(String jobString, Duration delay) {
        this.jobString = jobString;
        this.delay = delay;
//        this.endTime = endTime;
    }
}
