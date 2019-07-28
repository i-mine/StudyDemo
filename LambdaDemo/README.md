# Java Lambda表达式
## Lambda表达式与匿名内部类
不同点：
- lambda表达式的出现可以取代匿名内部类，关键的不同在于`this`,匿名内部类中的this指向的是匿名内部类，而lambda表达式中的this表示表达式所在类。
- 编译方式的差别，lambda表达式会被编译为类的私有方法。
## Lambda表达式的使用场景

lambda表达式仅能放入如下代码：预定义使用了 @Functional 注释的函数式接口，自带一个抽象函数的方法，或者SAM（Single Abstract Method 单个抽象方法）类型。这些称为lambda表达式的目标类型，可以用作返回类型，或lambda目标代码的参数。例如，若一个方法接收Runnable、Comparable或者 Callable 接口，都有单个抽象方法，可以传入lambda表达式。类似的，如果一个方法接受声明于 java.util.function 包内的接口，例如 Predicate、Function、Consumer 或 Supplier，那么可以向其传lambda表达式。

简而言之：

1.方法入参参数是带有单个抽象方法的抽象类，可以传入lambda表达式定义抽象方法。

2.方法入参参数是java.util.function 包内的接口，可以传入lambda表达式定义行为。

3.集合的操作使用Stream的方式可以使用lambda表达式。

注：Lambda表达式在可以引入表达式外部的变量，但是不可进行值的更改。
