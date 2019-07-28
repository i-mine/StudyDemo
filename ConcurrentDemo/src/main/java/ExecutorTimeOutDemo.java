import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * author: lanrish
 * date  : 2019-06-28
 * desc  :
 * <pre>
 *     用来测试线程池线程超时逻辑
 * </pre>
 */
public class ExecutorTimeOutDemo {
    public static void main(String[] args) {
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("dr-el-executor-cluster-thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue(), factory);

        for (int i = 0; i < 3; i++) {
            try {

                Future<String> future = threadPoolExecutor.submit(new FutureExecutor(1+i*2));
                try {
                    future.get(2, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    System.out.println("线程超时");
                    future.cancel(true);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
        }}
        threadPoolExecutor.shutdown();

    }

}

class FutureExecutor implements Callable {
    private int time = 0;

    public FutureExecutor(int time) {
        this.time = time;
    }

    public String call() throws Exception {
        Thread.sleep(this.time*1000);
        System.out.println(Thread.currentThread().getName() + ": I'm working..."+this.time+"s");
        return null;
    }
}
