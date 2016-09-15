package corp.wmsoft.android.lib.mvpcrx.interactor;

import corp.wmsoft.android.lib.mvpcrx.util.IMVPCSchedulerProvider;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * @param <Q> the request type
 * @param <T> the response type
 */

public abstract class MVPCUseCase<Q extends MVPCUseCase.RequestValues, T> {

    /**/
    private final IMVPCSchedulerProvider mMVPCSchedulerProvider;


    public MVPCUseCase(IMVPCSchedulerProvider schedulerProvider) {
        this.mMVPCSchedulerProvider = schedulerProvider;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link MVPCUseCase}.
     */
    public abstract Observable<T> buildUseCaseObservable(Q requestValues);

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable(Q)}.
     */
    public Subscription execute(Q requestValues, Observer<? super T> useCaseSubscriber) {
        return this.buildUseCaseObservable(requestValues)
                .subscribeOn(mMVPCSchedulerProvider.io())
                .observeOn(mMVPCSchedulerProvider.ui())
                .subscribe(useCaseSubscriber);
    }

    public Subscription execute(Q requestValues, final Action1<? super T> useCaseOnNext) {
        return this.buildUseCaseObservable(requestValues)
                .subscribeOn(mMVPCSchedulerProvider.io())
                .observeOn(mMVPCSchedulerProvider.ui())
                .subscribe(useCaseOnNext);
    }

    /**
     * Data passed to a request.
     */
    public static class RequestValues {  }
}
