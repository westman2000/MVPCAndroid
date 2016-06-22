package corp.wmsoft.android.lib.mvpcandroid.interactor;


/**
 * Runs {@link UseCase}'s using a {@link IUseCaseScheduler}.
 */
public class UseCaseHandler {

    /**/
    private static UseCaseHandler INSTANCE;
    /**/
    private final IUseCaseScheduler mUseCaseScheduler;


    /**
     *
     */
    public UseCaseHandler(IUseCaseScheduler useCaseScheduler) {
        this.mUseCaseScheduler = useCaseScheduler;
    }

    /**
     *
     */
    public <T extends UseCase.RequestValues, R extends UseCase.ResponseValue> void execute( final UseCase<T, R> useCase, T values, UseCase.IUseCaseCallback<R> callback) {
        useCase.setRequestValues(values);
        useCase.setUseCaseCallback(new UiCallbackWrapper<>(callback, this));

        mUseCaseScheduler.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run();
            }
        });
    }

    /**
     *
     */
    public <V extends UseCase.ResponseValue> void notifyResponse(final V response, final UseCase.IUseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }

    /**
     *
     */
    private <V extends UseCase.ResponseValue> void notifyError(final Error error, final UseCase.IUseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.onError(error, useCaseCallback);
    }

    /**
     *
     * @param <V>
     */
    private class UiCallbackWrapper<V extends UseCase.ResponseValue> implements UseCase.IUseCaseCallback<V> {

        /**/
        private final UseCase.IUseCaseCallback<V> mCallback;
        /**/
        private final UseCaseHandler mUseCaseHandler;


        /**
         *
         */
        public UiCallbackWrapper(UseCase.IUseCaseCallback<V> callback, UseCaseHandler useCaseHandler) {
            mCallback = callback;
            mUseCaseHandler = useCaseHandler;
        }

        /**
         *
         */
        @Override
        public void onSuccess(V response) {
            mUseCaseHandler.notifyResponse(response, mCallback);
        }

        /**
         *
         */
        @Override
        public void onError(Error error) {
            mUseCaseHandler.notifyError(error, mCallback);
        }
    }

    /**
     *
     * @return sigleton instance of {@link UseCaseHandler}
     */
    public static UseCaseHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }
}
