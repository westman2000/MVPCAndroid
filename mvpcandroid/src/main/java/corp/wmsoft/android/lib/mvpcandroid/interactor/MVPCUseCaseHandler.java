package corp.wmsoft.android.lib.mvpcandroid.interactor;


/**
 * Runs {@link MVPCUseCase}'s using a {@link IMVPCUseCaseScheduler}.
 */
public class MVPCUseCaseHandler {

    /**/
    private static MVPCUseCaseHandler INSTANCE;
    /**/
    private final IMVPCUseCaseScheduler mUseCaseScheduler;


    /**
     *
     */
    public MVPCUseCaseHandler(IMVPCUseCaseScheduler useCaseScheduler) {
        this.mUseCaseScheduler = useCaseScheduler;
    }

    /**
     *
     */
    public <T extends MVPCUseCase.RequestValues, R extends MVPCUseCase.ResponseValue> void execute(final MVPCUseCase<T, R> useCase, T values, MVPCUseCase.IUseCaseCallback<R> callback) {
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
    public <V extends MVPCUseCase.ResponseValue> void notifyResponse(final V response, final MVPCUseCase.IUseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback);
    }

    /**
     *
     */
    private <V extends MVPCUseCase.ResponseValue> void notifyError(final Error error, final MVPCUseCase.IUseCaseCallback<V> useCaseCallback) {
        mUseCaseScheduler.onError(error, useCaseCallback);
    }

    /**
     *
     * @param <V>
     */
    private class UiCallbackWrapper<V extends MVPCUseCase.ResponseValue> implements MVPCUseCase.IUseCaseCallback<V> {

        /**/
        private final MVPCUseCase.IUseCaseCallback<V> mCallback;
        /**/
        private final MVPCUseCaseHandler mUseCaseHandler;


        /**
         *
         */
        public UiCallbackWrapper(MVPCUseCase.IUseCaseCallback<V> callback, MVPCUseCaseHandler useCaseHandler) {
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
     * @return sigleton instance of {@link MVPCUseCaseHandler}
     */
    public static MVPCUseCaseHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MVPCUseCaseHandler(new MVPCUseCaseThreadPoolScheduler());
        }
        return INSTANCE;
    }
}
