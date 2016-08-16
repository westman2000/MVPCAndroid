package corp.wmsoft.android.lib.mvpc.interactor;

/**
 * Use cases are the entry points to the domain layer.
 *
 * @param <Q> the request type
 * @param <P> the response type
 */
public abstract class MVPCUseCase<Q extends MVPCUseCase.RequestValues, P extends MVPCUseCase.ResponseValue> {

    private Q mRequestValues;

    private IUseCaseCallback<P> mUseCaseCallback;

    /**
     *
     */
    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    /**
     *
     */
    public Q getRequestValues() {
        return mRequestValues;
    }

    /**
     *
     */
    public IUseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    /**
     *
     */
    public void setUseCaseCallback(IUseCaseCallback<P> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    /**
     *
     */
    void run() {
       executeUseCase(mRequestValues);
    }

    /**
     *
     */
    protected abstract void executeUseCase(Q requestValues);

    /**
     * Data passed to a request.
     */
    public static class RequestValues {  }

    /**
     * Data received from a request.
     */
    public static class ResponseValue { }


    /***********************************************************************
     *
     */
    public interface IUseCaseCallback<R> {

        void onSuccess(R response);

        void onError(Error error);

    }
}
