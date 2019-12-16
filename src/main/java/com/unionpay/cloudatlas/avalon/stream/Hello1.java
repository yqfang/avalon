package com.unionpay.cloudatlas.avalon.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * stream 基本和并发操作
 */
public class Hello1 {
    public static void main(String[] args) throws Exception {
        String[] arr = new String[]{"a", "b", "c"};
        List<String> arrList = Arrays.asList(arr);
        Stream<String> multiThreadStream = arrList.parallelStream();
        Stream<String> plainStream = arrList.stream();
        System.out.println("current thread id: " + Thread.currentThread().getId());
        multiThreadStream.forEach( e -> doWork(e) );
        Thread.sleep(1000);
        System.out.println("------------");
        plainStream.forEach( e -> doWork(e) );
    }
    public static <T> void doWork(T e) {
        System.out.println(Thread.currentThread().getId());
        System.out.println(e);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
