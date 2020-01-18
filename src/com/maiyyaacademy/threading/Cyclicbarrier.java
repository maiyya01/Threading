package com.maiyyaacademy.threading;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker1 implements Runnable
{
    private int id;
    private Random random;
    private CyclicBarrier cyclicbarrier;

    public Worker1(int id, CyclicBarrier cyclicbarrier)
    {
        this.cyclicbarrier = cyclicbarrier;
        random = new Random();
        this.id = id;
    }

    @Override
    public void run()
    {
        doWork();

    }

    private void doWork() {
        System.out.println("Thread with ID "+ id + " starts the task...");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with ID " + id + " finished....");
        try {
            cyclicbarrier.await();
            System.out.println("After await");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public String toString()
    {
        return ""+ this.id;
    }

}

public class Cyclicbarrier
{

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All the tasks are finished");

            }
        });

        for ( int i =0; i < 5; i++)
        {
            executorService.execute(new Worker1(i+1, barrier));
        }

        executorService.shutdown();


    }
}
