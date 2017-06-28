package com.yumao.yumaosmart.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by kk on 2017/2/23.
 */

public class ThreadPoorProxy {
    public static ThreadPoolExecutor mExecutor;

    public static ThreadPoolExecutor getInstance() {
        if (mExecutor == null) {
            synchronized (ThreadPoorProxy.class) {
                if (mExecutor == null) {
                    mExecutor = new ThreadPoolExecutor(3, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3));
                }

            }

        }
        return mExecutor;
    }

    public static void  execute(Runnable runnable) {
        mExecutor.execute(runnable);
    }

}
