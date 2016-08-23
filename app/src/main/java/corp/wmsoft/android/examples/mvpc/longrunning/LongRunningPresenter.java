package corp.wmsoft.android.examples.mvpc.longrunning;

import corp.wmsoft.android.examples.mvpc.longrunning.iteractor.LongRunningUseCase;
import corp.wmsoft.android.lib.mvpc.interactor.MVPCUseCase;
import corp.wmsoft.android.lib.mvpc.interactor.MVPCUseCaseHandler;
import corp.wmsoft.android.lib.mvpc.presenter.MVPCPresenter;
import hugo.weaving.DebugLog;


/**
 * Created by admin on 8/5/16.
 *
 */
@DebugLog
public class LongRunningPresenter extends MVPCPresenter<LongRunningContract.View> implements LongRunningContract.Presenter, MVPCUseCase.IUseCaseCallback<LongRunningUseCase.ResponseValue> {

    /**
     * Use cases
     */
    private final LongRunningUseCase mLongRunningUseCase;
    /**/
    private boolean isLoading;
    /**/
    private String message;


    public LongRunningPresenter(
            MVPCUseCaseHandler useCaseHandler,
            LongRunningUseCase longRunningUseCase
    ) {
        super(useCaseHandler);
        this.mLongRunningUseCase = longRunningUseCase;
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
    }

    @Override
    public void onSuccess(LongRunningUseCase.ResponseValue response) {
        getView().hideLoading();
        isLoading = false;
        message = response.getResponseMessage();
        getView().showMessage(message);
    }

    @Override
    public void onError(Error error) {
        getView().hideLoading();
        isLoading = false;
        getView().showMessage(error.getMessage());
    }

    /**
     * Called by Data Binding library.
     */
    @Override
    public void onStartUseCase() {
        if (getUseCaseHandler() != null && !isLoading) {

            getView().showLoading();
            isLoading = true;

            LongRunningUseCase.RequestValues requestValues = new LongRunningUseCase.RequestValues();
            getUseCaseHandler().execute(mLongRunningUseCase, requestValues, this);
        }

    }

}