package com.maiyyaacademy.threading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

class FirstWorker implements Runnable
{
    private BlockingQueue<Integer> blockingDeque;

    public FirstWorker(BlockingQueue<Integer> blockingDeque)
    {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run()
    {
        int counter = 0;

        while(true)
        {
            try {
                blockingDeque.put(counter);
                System.out.println("Putting item to the queue" + counter);
                counter++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class SecondWorker implements Runnable
{
    private BlockingQueue<Integer> blockingDeque;

    public SecondWorker(BlockingQueue<Integer> blockingDeque)
    {
        this.blockingDeque = blockingDeque;
    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                int number = blockingDeque.take();
                System.out.println("Taking number from queue:" + number);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

public class TheBlockingQueue
{


    public static void main(String[] args)
    {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);
        new Thread(firstWorker).start();
        new Thread(secondWorker).start();


    }
}
