import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author: dulei
 * date: 18-7-23
 * desc:
 */
public class    ExecutorDemo {
    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("dr-el-executor-cluster-thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), factory);
        boolean flag = true;
        while (true) {
            try {
                if (flag) {
                    flag = false;
                    threadPoolExecutor.submit(new Executor());
                    threadPoolExecutor.submit(new Executor());
                    threadPoolExecutor.submit(new Executor());
                }
                System.out.println(System.currentTimeMillis());
                Thread.sleep(10000);
                System.out.println("I will updated");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Executor implements Runnable {

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + ": hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}