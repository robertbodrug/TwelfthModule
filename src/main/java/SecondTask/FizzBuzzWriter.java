package SecondTask;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.SynchronousQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

public class FizzBuzzWriter {
    private StringBuffer sb = new StringBuffer();
    private  volatile long counter=1;
    private final long n;

    public FizzBuzzWriter(long n) {
        this.n = n;
    }

    public void run() throws InterruptedException {
        Thread A = new Thread(() -> {
            try {
                fizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread B = new Thread(()-> {
            try {
                buzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread C = new Thread(() -> {
            try {
                fizzbuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread D = new Thread(()-> {
            try {
                numbers();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        A.start();
        B.start();
        C.start();
        D.start();


        A.join();
        B.join();
        C.join();
        D.join();

        System.out.println(sb.delete(sb.length()-2,sb.length()).toString());
    }



    public synchronized void fizzbuzz() throws InterruptedException {

        while (counter<=n){
            if(counter%3==0&&counter%5==0){
                sb.append("fizzbuzz");
                sb.append(" ,");
                counter++;
                notifyAll();
            }else {
                wait();
            }
        }
    }
    public synchronized void buzz() throws InterruptedException {
        while (counter<=n){
            if(counter%5==0&&counter%3!=0){
                sb.append("buzz");
                sb.append(" ,");
                counter++;
                notifyAll();
            }else {
                wait();
            }
        }
    }
    public synchronized void fizz() throws InterruptedException {
        while (counter<=n){
            if(counter%3==0&&counter%5!=0){
                sb.append("fizz");
                sb.append(" ,");
                counter++;
                notifyAll();
            }else {
                wait();
            }
        }
    }
    public synchronized void numbers() throws InterruptedException {
        while (counter<=n){
            if(counter%3!=0&&counter%5!=0){
                sb.append(counter);
                sb.append(" ,");
                counter++;
                notifyAll();
            }else {
                wait();
            }
        }
    }
    }


