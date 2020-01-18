package com.maiyyaacademy.threading;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable
{

    private int id;
    private CountDownLatch countDownLatch;
    private Random random;

    public Worker(int id, CountDownLatch countDownLatch)
    {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
    {
        doWork();
        countDownLatch.countDown();
    }

    private void doWork()
    {
        System.out.println(" Thread with id" + this.id + " starts working...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class BarrierLatch {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        CountDownLatch countDownLatch = new CountDownLatch(5);
        for ( int i =0; i<5; i++)
        {
            executorService.execute(new Worker(i+1, countDownLatch));
        }

        countDownLatch.await();
        System.out.println(" All the prerequisites are done..");
        executorService.shutdown();

    }
}
