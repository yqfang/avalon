package com.unionpay.cloudatlas.avalon.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * stream
 */
public class Hello2 {
    public static void main(String[] args) throws Exception {
        String[] arr = new String[]{"a", "b", "c", "c", "d"};
        List<String> arrList = Arrays.asList(arr);
        System.out.println(arrList.stream().distinct().count());
    }
}
