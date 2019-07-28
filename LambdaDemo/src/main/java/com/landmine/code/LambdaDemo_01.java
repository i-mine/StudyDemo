package com.landmine.code;

/**
 * author: lanrish
 * date  : 2019-07-24
 * desc  :
 * <pre>
 *     lambda实现Runnable匿名类
 * </pre>
 */
public class LambdaDemo_01 {
    public static void main(String[] args) {
        //Old way
        new Thread(new Runnable() {
            public void run() {
                System.out.println("Thread is Running!");
            }
        }).start();
        //New way
        new Thread(()-> System.out.println("Lambda Thread is Running")).start();
    }

}
