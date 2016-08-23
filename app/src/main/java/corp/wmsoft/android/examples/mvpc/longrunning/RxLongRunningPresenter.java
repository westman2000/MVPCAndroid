package corp.wmsoft.android.examples.mvpc.longrunning;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import corp.wmsoft.android.lib.mvpc.presenter.MVPCPresenter;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by admin on 8/5/16.
 *
 */
public class RxLongRunningPresenter extends MVPCPresenter<LongRunningContract.View> implements LongRunningContract.Presenter {

    /**/
    private boolean isLoading;
    /**/
    private String message;
    /**/
    private Observable<String> mObservable;
    /**/
    private Subscription mSubscription;


    public RxLongRunningPresenter(
    ) {
        super(null);
        isLoading = false;
        message = "";
        init();
    }

    @Override
    public void attachView(LongRunningContract.View mvpView) {
        super.attachView(mvpView);
        if (isLoading)
            getView().showLoading();
        else {
            getView().hideLoading();
            getView().showMessage(message);
        }
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onDestroyed() {
        super.onDestroyed();
        if (mSubscription != null && !mSubscription.isUnsubscribed())
            mSubscription.unsubscribe();
    }

    /**
     * Called by Data Binding library.
     */
    @Override
    public void onStartUseCase() {
//        if (!isLoading) {

            getView().showLoading();
            isLoading = true;

            mSubscription = mObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onCompleted() {
                            getView().hideLoading();
                            isLoading = false;
                            getView().showMessage(message);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("RxLongRunning","onError("+e+")");
                            isLoading = false;

                            if (isViewAttached()) {
                                getView().hideLoading();
                                getView().showMessage(e.getMessage());
                            }
                        }

                        @Override
                        public void onNext(String s) {
                            message += s + "\n";
                            getView().showMessage(message);
                        }
                    });
//        }
    }

    private void init() {
        mObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    int i = 0;
                    while (i < 14) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            subscriber.onError(new Error("was error"));
                        }
                        subscriber.onNext("observable on next "+i);
//                        if (i == 7) throw new Error("test error!!!");
                        i++;
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

}