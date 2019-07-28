package com.landmine.code;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * author: lanrish
 * date  : 2019-07-24
 * desc  :
 * <pre>
 *     lambda表达式自定义Comparator
 * </pre>
 */
public class LambdaDemo_02 {
    public static void main(String[] args) {
        Integer[] arry = new Integer[]{1,5,7,8,2,9};
        List<Integer> list = Arrays.asList(arry);
        //order by asc:[1, 2, 5, 7, 8, 9]
        Collections.sort(list);
        System.out.println(list);
        //1. Old way oder by desc:
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(list);

        //2. New way order by desc:
        System.out.println("----------Lambda Comparator---------");

        System.out.println("Shuffle First");
        Collections.shuffle(list);
        System.out.println(list);

        Collections.sort(list, (Integer o1,Integer o2)-> o2.compareTo(o1));
//        Collections.sort(list, Comparator.reverseOrder()); More simple way
        System.out.println(list);
    }
}
