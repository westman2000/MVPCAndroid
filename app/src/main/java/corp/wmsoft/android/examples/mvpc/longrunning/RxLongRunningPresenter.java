package corp.wmsoft.android.examples.mvpc.longrunning;

import android.util.Log;

import corp.wmsoft.android.examples.mvpc.longrunning.iteractor.DelayedUseCase;
import corp.wmsoft.android.examples.mvpc.longrunning.iteractor.LongRunningUseCase;
import corp.wmsoft.android.lib.mvpc.presenter.MVPCPresenter;
import rx.Observer;


/**
 * Created by admin on 8/5/16.
 *
 */
public class RxLongRunningPresenter extends MVPCPresenter<LongRunningContract.View> implements LongRunningContract.Presenter {

    /**/
    private DelayedUseCase mDelayedUseCase;
    /**/
    private boolean isLoading;
    /**/
    private String message;


    public RxLongRunningPresenter(DelayedUseCase delayedUseCase) {
        mDelayedUseCase = delayedUseCase;
        isLoading = false;
        message = "";
    }

    @Override
    public void attachView(LongRunningContract.View mvpView) {
        super.attachView(mvpView);
        if (isLoading)
            getView().showLoading();
        else {
            getView().hideLoading();
        }
        getView().showMessage(message);
    }

    /**
     * Called by Data Binding library.
     */
    @Override
    public void onStartUseCase() {
        getView().showLoading();
        isLoading = true;
        message = "";

        this.executeUseCase(mDelayedUseCase, new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("RxLongRunning","onCompleted()");
                if (isViewAttached()) {
                    getView().hideLoading();
                    getView().showMessage(message);
                }
                isLoading = false;
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RxLongRunning","onError("+e+")");
                e.printStackTrace();
                isLoading = false;

                if (isViewAttached()) {
                    getView().hideLoading();
                    getView().showMessage(e.getMessage());
                }
            }

            @Override
            public void onNext(String s) {
                Log.i("RxLongRunning","onNext("+s+")");
                message += s + "\n";
                if (isViewAttached()) {
                    getView().showMessage(message);
                }
            }
        });

    }

}