package com.wang.provider.jconcurrent.chapter8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(0, 5,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 15; i++) {
            String s = String.valueOf(i);
            executorService.submit(() -> {
                System.out.println("execute task "+s+" ====");
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
