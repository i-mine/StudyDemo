package com.landmine.code;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * author: lanrish
 * date  : 2019-07-24
 * desc  :
 * <pre>
 *     lambda表达式和函数式接口Predicate(过滤操作)
 * </pre>
 */
public class LambdaDemo_04 {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java", "Bash", "C++", "Scala");

        System.out.println("Languages which start with J :");
        filter(languages, (str) -> ((String) str).startsWith("J"));

        System.out.println("Languages which end with a :");
        filter(languages, (str) -> (((String) str).endsWith("a")));

        System.out.println("Print all languages : ");
        filter(languages, (str) -> true);

        System.out.println("Print no languages : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4 : ");
        filter(languages, (str) -> (((String) str).length() > 4));

        //以上可以将具体的判断逻辑注入到Predicate,多个条件可以使用and(),or()进行组合
        Predicate<String> startWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        languages.stream()
                .filter(fourLetterLong.and(startWithJ))
                .forEach((n)-> System.out.println("Language which start with J and four letter long is :"+ n));
    }

    @SuppressWarnings("unchecked")
    private static void filter(List<String> names, Predicate condition) {
        names.forEach(name -> {
            if (condition.test(name)) {
                System.out.println(name);
            }
        });
    }
}
