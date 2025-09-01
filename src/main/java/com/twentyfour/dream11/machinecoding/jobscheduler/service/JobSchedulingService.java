package com.twentyfour.dream11.machinecoding.jobscheduler.service;

import com.twentyfour.dream11.machinecoding.jobscheduler.entities.Job;
import com.twentyfour.dream11.machinecoding.jobscheduler.exceptions.CustomException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class JobSchedulingService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final PriorityBlockingQueue<Job> pq = new PriorityBlockingQueue<>(11, Comparator.comparing(Job::getNextExecution));
    private final int SIZE;

    public JobSchedulingService(int size) {
        SIZE = size;
    }

    public void addJob(Job job) throws CustomException {
        synchronized (this) {
            if (pq.size() == SIZE) {
                throw new CustomException("Max jobs limit reached");
            }
            Instant nextExecutionInstant = Instant.now().plus(job.getDelay());
            job.setNextExecution(nextExecutionInstant);
            pq.add(job);
        }
    }

    public void consumeJob() {
        executorService.execute(() -> {
            try {
                while (true) {
                    Job nextJob;
                    synchronized (this) {
                        nextJob = pq.peek();
                        if (nextJob == null) {
                            System.out.println("No jobs to run");
                            return;
                        }
                    }
                    long waitTime = Instant.now().until(nextJob.getNextExecution(), ChronoUnit.MILLIS);
                    if (waitTime > 0) {
                        Thread.sleep(waitTime);
                    }

                    synchronized (this) {
                        pq.poll(); // Remove the job from the queue
                    }

                    Job rescheduledJob = JobHelper.executeAndSchedule(nextJob);
                    synchronized (this) {
                        pq.add(rescheduledJob); // Reschedule the job
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception occurred while performing job. Exception: " + e);
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
