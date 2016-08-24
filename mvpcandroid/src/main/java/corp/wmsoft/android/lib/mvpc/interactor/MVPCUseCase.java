package corp.wmsoft.android.lib.mvpc.interactor;

import corp.wmsoft.android.lib.mvpc.util.IMVPCSchedulerProvider;
import rx.Observable;
import rx.Observer;
import rx.Subscription;


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */

public abstract class MVPCUseCase<T> {

    /**/
    private final IMVPCSchedulerProvider mMVPCSchedulerProvider;


    public MVPCUseCase(IMVPCSchedulerProvider schedulerProvider) {
        this.mMVPCSchedulerProvider = schedulerProvider;
    }

    /**
     * Builds an {@link rx.Observable} which will be used when executing the current {@link MVPCUseCase}.
     */
    protected abstract Observable<T> buildUseCaseObservable();

    /**
     * Executes the current use case.
     *
     * @param useCaseSubscriber The guy who will be listen to the observable build
     * with {@link #buildUseCaseObservable()}.
     */
    public Subscription execute(Observer<? super T> useCaseSubscriber) {
        return this.buildUseCaseObservable()
                .subscribeOn(mMVPCSchedulerProvider.io())
                .observeOn(mMVPCSchedulerProvider.ui())
                .subscribe(useCaseSubscriber);
    }

}
