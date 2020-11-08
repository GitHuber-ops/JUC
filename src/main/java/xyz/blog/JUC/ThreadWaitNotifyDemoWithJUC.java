package xyz.blog.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project: JUC
 * @Author: Unknown
 * @Create: 2020--09--06--3:02 PM
 */
class AirConditionerWithJUC {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public synchronized void increment() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (number != 0) {
                condition.await();
            }
            //2.操作
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void decrement() throws InterruptedException {
        lock.lock();
        try {
            //1.判断
            while (number != 0) {
                condition.await();
            }
            //2.操作
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadWaitNotifyDemoWithJUC {
    public static void main(String[] args) {
        AirConditionerWithJUC airConditionerWithJUC = new AirConditionerWithJUC();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    airConditionerWithJUC.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"thread1").start();


        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    airConditionerWithJUC.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"thread2").start();
    }
}
