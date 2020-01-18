package com.maiyyaacademy.threading.DiningPhilospherProblem;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philospher implements Runnable
{

    private int id;
    private Chopstick leftChopstick;
    private Chopstick rightChopstick;
    private Random random;
    private int eatingCounter;
    private volatile boolean isFull = false;

    public Philospher(int id, Chopstick leftChopstick, Chopstick rightChopstick)
    {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    @Override
    public void run() {

        try {
            while (!isFull) {
                think();
                if ( leftChopstick.pickUp(this, State.LEFT))
                {
                    if ( rightChopstick.pickUp(this, State.RIGHT))
                    {
                        eat();
                        rightChopstick.putDown(this, State.RIGHT);
                    }
                    leftChopstick.putDown(this, State.LEFT);
                }

            }

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException
    {
        System.out.println("Philospher is thinking");
        Thread.sleep(random.nextInt(1000));

    }

    private void eat() throws InterruptedException
    {
        System.out.println(this + " is eating");
        this.eatingCounter++;
        Thread.sleep(random.nextInt(1000));

    }

    public int getCounter()
    {
        return eatingCounter;
    }

    public void setFull(boolean isFull)
    {
        this.isFull = isFull;
    }

    @Override
    public String toString()
    {
        return "Philosopher " + id;
    }

}
