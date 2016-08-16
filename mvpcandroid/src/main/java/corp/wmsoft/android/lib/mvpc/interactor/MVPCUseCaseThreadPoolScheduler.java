package corp.wmsoft.android.lib.mvpc.interactor;

import android.os.Handler;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Executes asynchronous tasks using a {@link ThreadPoolExecutor}.
 * <p>
 * See also {@link Executors} for a list of factory methods to create common
 * {@link java.util.concurrent.ExecutorService}s for different scenarios.
 */
public class MVPCUseCaseThreadPoolScheduler implements IMVPCUseCaseScheduler {

    /**/
    private final Handler mHandler = new Handler();
    /**/
    public static final int POOL_SIZE = 2;
    /**/
    public static final int MAX_POOL_SIZE = 4;
    /**/
    public static final int TIMEOUT = 30;
    /**/
    ThreadPoolExecutor mThreadPoolExecutor;

    /**
     *
     */
    public MVPCUseCaseThreadPoolScheduler() {
        mThreadPoolExecutor = new ThreadPoolExecutor(POOL_SIZE, MAX_POOL_SIZE, TIMEOUT, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    /**
     *
     */
    @Override
    public void execute(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    /**
     *
     */
    @Override
    public <V extends MVPCUseCase.ResponseValue> void notifyResponse(final V response, final MVPCUseCase.IUseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onSuccess(response);
            }
        });
    }

    /**
     *
     */
    @Override
    public <V extends MVPCUseCase.ResponseValue> void onError(final Error error, final MVPCUseCase.IUseCaseCallback<V> useCaseCallback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                useCaseCallback.onError(error);
            }
        });
    }

}
