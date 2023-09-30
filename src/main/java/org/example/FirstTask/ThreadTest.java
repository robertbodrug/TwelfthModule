package org.example.FirstTask;

import java.time.Duration;

public class ThreadTest {
    public static void main(String[] args) {
        new Thread(()->{
            long time1
                    =0;
            while(true) {
                try {
                    Thread.sleep(Duration.ofSeconds(5));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                time1+=5;
                System.out.println("Минуло " + time1  + " секунд" );
            }
        }).start();
        long time=0;
        while(true) {
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(time += 1 );
        }

    }
}
