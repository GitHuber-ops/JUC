package xyz.blog.JUC;

/**
 * @Project: JUC
 * @Author: Unknown
 * @Create: 2020--09--06--11:10 AM
 */

class AirConditioner {

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //1.判断
        while (number != 0) {
            this.wait();
        }
        //2.操作
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3.通知
        this.notify();
    }

    public synchronized void decrement() throws InterruptedException {
        //1.判断
        while (number == 0) {
            this.wait();
        }
        //2.操作
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3.通知
        this.notify();
    }
}

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    airConditioner.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"thread1").start();


        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    airConditioner.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"thread2").start();
    }
}

