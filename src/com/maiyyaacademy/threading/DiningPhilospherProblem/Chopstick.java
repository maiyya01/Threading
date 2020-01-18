package com.maiyyaacademy.threading.DiningPhilospherProblem;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick
{

    private int id;
    private Lock lock;

    public Chopstick(int id)
    {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philospher philospher, State state)
    {
        try {
            if ( lock.tryLock(10, TimeUnit.MILLISECONDS))
            {
                System.out.println(philospher + " picked up " + state.toString() + "" + this );
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void putDown(Philospher philospher, State state)
    {
        lock.unlock();
        System.out.println(philospher + " put down " + this);

    }

    @Override
    public String toString()
    {
        return "ChopStick:" + id;
    }
}
