package com.landmine.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: lanrish
 * date  : 2019-07-24
 * desc  :
 * <pre>
 *     lambda迭代集合和Map
 * </pre>
 */
public class LambdaDemo_03 {
    public static void main(String[] args) {
        //-------------遍历集合----------------------
        List<String> regions = Arrays.asList("hk", "beijing", "changsha");

        //Java 8 Before
        for (String region : regions) {
            System.out.println(region);
        }
        //Java8 After
//        regions.forEach(region-> System.out.println(region));
        regions.forEach(System.out::println);

        //---------------遍历Map-----------------
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);

        //Java 8 Before
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.println("item:" + entry.getKey() + " Count:" + entry.getValue());
        }

        //Java 8 After
        items.forEach((k, v) -> System.out.println("item: " + k + " Count:" + v));
    }
}
