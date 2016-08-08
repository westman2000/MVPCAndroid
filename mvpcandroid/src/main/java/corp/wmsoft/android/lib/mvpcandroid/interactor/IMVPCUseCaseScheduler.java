package corp.wmsoft.android.lib.mvpcandroid.interactor;

/**
 * Interface for schedulers, see {@link MVPCUseCaseThreadPoolScheduler}.
 */
public interface IMVPCUseCaseScheduler {

    /**
     *
     */
    void execute(Runnable runnable);

    /**
     *
     */
    <V extends MVPCUseCase.ResponseValue> void notifyResponse(final V response, final MVPCUseCase.IUseCaseCallback<V> useCaseCallback);

    /**
     *
     */
    <V extends MVPCUseCase.ResponseValue> void onError(final Error error, final MVPCUseCase.IUseCaseCallback<V> useCaseCallback);

}