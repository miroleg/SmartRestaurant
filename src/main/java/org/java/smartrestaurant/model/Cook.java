package org.java.smartrestaurant.model;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private final String name;
    private LinkedBlockingQueue<OrderU> queue;

    public LinkedBlockingQueue<OrderU> getQueue() {
        return queue;
    }

    public void setQueue(LinkedBlockingQueue<OrderU> queue) {
        this.queue = queue;
    }

    private boolean busy;

    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void startCookingOrder(OrderU order) {
        this.busy = true;

        User user = order.getUser();

//   TODO  log   writeMessage(name + " Start cooking - " + order);

        int totalCookingTime = order.getTotalCookingTime();


        try {
            Thread.sleep(totalCookingTime * 10);
        } catch (InterruptedException ignored) {
        }

        setChanged();
        notifyObservers(order);
        this.busy = false;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(10);
                if (!queue.isEmpty()) {
                    if (!this.isBusy()) {
                        this.startCookingOrder(queue.take());
                    }
                }
            }
        } catch (InterruptedException e) {

        }

    }

}
