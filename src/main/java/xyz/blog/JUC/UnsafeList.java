package xyz.blog.JUC;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Project: JUC
 * @Author: Unknown
 * @Create: 2020--09--06--6:24 PM
 */
public class UnsafeList {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        new Thread(task).start();
        System.out.println(task.get());



    }
}
