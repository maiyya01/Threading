package com.maiyyaacademy.threading;

public class WaitAndNotify
{
    public void produce() throws InterruptedException
    {

        synchronized (this)
        {
            System.out.println(" We are in producer method");
            wait();
            System.out.println(" Again in producer method");
        }

    }

    public void consume() throws InterruptedException
    {
        Thread.sleep(1000);
        synchronized (this)
        {
            System.out.println("Consumer method");
            notify();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        WaitAndNotify object = new WaitAndNotify();
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run() {

                try {
                    object.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });
        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run() {

                try {
                    object.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
