package com.landmine.code;

import java.util.Arrays;
import java.util.List;

/**
 * author: lanrish
 * date  : 2019-07-24
 * desc  :
 * <pre>
 *     lambda map 和 reduce
 * </pre>
 */
public class LambdaDemo_05 {
    public static void main(String[] args) {
        double total = 0;

        //不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total += price;
            System.out.println(price);
        }
        System.out.println("Total Price is:" + total);
        //使用lambda表达式
        double bill = costBeforeTax
                .stream()
                .map((cost) -> cost + .12 * cost)
                .reduce((sum, cost)-> sum + cost)
                .get();

        System.out.println("Total Price is:" + bill);
    }
}
