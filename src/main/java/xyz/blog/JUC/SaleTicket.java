package xyz.blog.JUC;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Project: JUC
 * @Author: Unknown
 * @Create: 2020--09--05--8:52 PM
 */

//具体的操作方法在类的内部实现，仅对外暴露方法调用接口(高内聚)
//遇见多并发先写资源类(低耦合)
class Ticket {
    private int number = 500;
    private Lock lock = new ReentrantLock();

    public synchronized void saleTicket() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.print(Thread.currentThread().getName());
                System.out.println("卖出第" + (number--) + "剩余" + number + "张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicket {
    public static void main(String[] args) {
        final Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.saleTicket();
        }, "thread1").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.saleTicket();
        }, "thread2").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) ticket.saleTicket();
        }, "thread3").start();


//        //接口也可以new，如匿名内部类
//        //可以利用代码模板将下面的东西写为模板
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        },"thread1").start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        },"thread2").start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.saleTicket();
//                }
//            }
//        },"thread3").start();
    }
}
