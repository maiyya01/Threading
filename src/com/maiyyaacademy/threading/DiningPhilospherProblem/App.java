package com.maiyyaacademy.threading.DiningPhilospherProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App
{
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philospher[] philosphers = null;
        try
        {
            philosphers = new Philospher[Constants.NUMBERS_OF_PHILOSPHERS];
            Chopstick[] chopsticks = new Chopstick[Constants.NUMBERS_OF_CHOPSTICKS];

            for ( int i = 0; i< Constants.NUMBERS_OF_CHOPSTICKS; i++)
            {
                chopsticks[i] = new Chopstick(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.NUMBERS_OF_PHILOSPHERS);

            for ( int i=0; i< Constants.NUMBERS_OF_PHILOSPHERS; i++)
            {
                philosphers[i] = new Philospher(i, chopsticks[i] , chopsticks[i+1 % Constants.NUMBERS_OF_CHOPSTICKS]);
                executorService.execute(philosphers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for(Philospher p : philosphers)
            {
                p.setFull(true);
            }

        }
        catch (Exception e)
        {
            executorService.shutdown();
            while( ! executorService.isTerminated())
                Thread.sleep(1000);

            for ( Philospher p : philosphers)
                System.out.println(p + " eats" + p.getCounter());
        }
    }

}
