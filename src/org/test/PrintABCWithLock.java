package org.test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * 通过不同的线程打印ABC，依次打印，循环10次
 *
 * @author: seancheer
 * @date: 2019/3/21
 **/
public class PrintABCWithLock {
    private static final Object LOCK = new Object();

    private static int count = 0;

    /**
     * print ABC with 3 threads
     * @param args
     */
    public static void main(String[] args)
    {
        startPrintABC();
    }


    /**
     * real start printABC method
     */
    public static void startPrintABC() {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK) {
                while (count < 30) {
                    try {
                        if (count % 3 == 0) {
                            System.out.print("A");
                            ++count;
                            LOCK.notifyAll();
                        } else {
                            LOCK.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK) {
                while (count < 30) {
                    try {
                        if (count % 3 == 1) {
                            System.out.print("B");
                            ++count;
                            LOCK.notifyAll();
                        } else {
                            LOCK.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        Thread t3 = new Thread(() -> {
            synchronized (LOCK) {
                while (count < 30) {
                    try {
                        if (count % 3 == 2) {
                            System.out.print("C");
                            ++count;
                            LOCK.notifyAll();
                        } else {
                            LOCK.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
