package org.example;

import static java.lang.Thread.sleep;

public class ProducerAndConsumer {
    boolean available = false;

    synchronized void producer() throws InterruptedException {
        while (available) wait();
        System.out.println("Produced");
        available = true;
        notify();
    }

    synchronized void consumer() throws InterruptedException {
        while (!available) wait();
        System.out.println("Consumed");
        available = false;
        notify();
    }

    public static void main(String[] args) {
        ProducerAndConsumer pc = new ProducerAndConsumer();
        Runnable producer =()->{
            try {
                while(true){
                    pc.producer();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Runnable consumer = ()->{
            try{
                while(true){
                    pc.consumer();
                    Thread.sleep(700);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        Thread p1 = new Thread(producer, "Producer Task");
        Thread c1 = new Thread(consumer, "Consumer Task");

        p1.start();
        c1.start();
    }
}

