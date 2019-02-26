package com.wang.provider.jconcurrent.chapter8;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) throws IOException {
        System.out.println(ListTest.class.getClassLoader().getResource("").getPath());
        System.out.println(ListTest.class.getClassLoader().getResource("/"));
        System.out.println(new File("").getCanonicalPath());
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        new Thread(()->{
            for (int i =0;i<10;i++){
                copyOnWriteArrayList.add(String.valueOf(i));
            }
        }).start();

        for (String v:copyOnWriteArrayList){
            System.out.println("v==="+v);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
