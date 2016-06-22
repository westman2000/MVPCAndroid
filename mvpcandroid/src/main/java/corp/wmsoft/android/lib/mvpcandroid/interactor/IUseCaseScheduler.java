package corp.wmsoft.android.lib.mvpcandroid.interactor;

/**
 * Interface for schedulers, see {@link UseCaseThreadPoolScheduler}.
 */
public interface IUseCaseScheduler {

    /**
     *
     */
    void execute(Runnable runnable);

    /**
     *
     */
    <V extends UseCase.ResponseValue> void notifyResponse(final V response, final UseCase.IUseCaseCallback<V> useCaseCallback);

    /**
     *
     */
    <V extends UseCase.ResponseValue> void onError(final Error error, final UseCase.IUseCaseCallback<V> useCaseCallback);

}