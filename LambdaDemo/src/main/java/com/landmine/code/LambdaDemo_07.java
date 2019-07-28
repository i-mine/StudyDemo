package com.landmine.code;

/**
 * author: lanrish
 * date  : 2019-07-25
 * desc  :
 * <pre>
 *     模拟Lambda使用场景：
 *     替代匿名内部类，且只拥有单个抽象方法
 * </pre>
 */
public class LambdaDemo_07 {
    public static void main(String[] args) {
        Screen screen = new Screen();
        screen.load(new ConsolePrinter() {
            @Override
            public void out(String string) {
                System.out.println(string);
            }
        },"Screen begin to work!");

        screen.load(System.out::println, "Screen begin to work!");

    }
}

class Screen {

    public void load(Printer printer, String string){
        printer.out(string);
    }
}
