package com.landmine.code;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * author: lanrish
 * date  : 2019-07-24
 * desc  :
 * <pre>
 *     lambda Stream Api收集器和统计器
 * </pre>
 */
public class LambdaDemo_06 {
    public static void main(String[] args) {
        //stream to list
        List<String> list = Stream.of("a", "b", "c", "d").map(x -> x.toUpperCase()).collect(Collectors.toList());
        System.out.println(list);

        //stream to string
        String str = Stream.of("a", "b", "c", "d").map(x -> x.toUpperCase()).collect(Collectors.joining(",", "--", "--"));
        System.out.println(str);

        //stream 去重
        List<Integer> integerList = Stream.of(1, 2, 2, 4, 6, 4).map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(integerList);

        //strean list to map
        List<Account> accountList = Lists.newArrayList(
                new Account("dulei", 24),
                new Account("lulu", 24)
        );
        Map<String, Account> accountMap = accountList
                .stream()
                .collect(Collectors.toMap(Account::getName, java.util.function.Function.identity()));
        System.out.println(accountMap);

        //统计器
        IntSummaryStatistics statistics =  Stream.of(1,4,326,85,34,2,5).mapToInt((x)->x).summaryStatistics();
        System.out.println("Max Num: " + statistics.getMax());
        System.out.println("Min Num: " + statistics.getMin());
        System.out.println("Sum Num: " + statistics.getSum());
        System.out.println("Avg Num: " + statistics.getAverage());
    }
}

class Account {
    private String name;
    private Integer age;

    public Account(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}