package corp.wmsoft.android.examples.mvpc;

import java.util.Locale;

import corp.wmsoft.android.lib.mvpc.interactor.MVPCUseCaseHandler;
import corp.wmsoft.android.lib.mvpc.presenter.MVPCPresenter;


/**
 * Created by admin on 8/5/16.
 *
 */
public class MainPresenter extends MVPCPresenter<MainContract.View> implements MainContract.Presenter {

    /**/
    private int mCounter;


    public MainPresenter(MVPCUseCaseHandler useCaseHandler) {
        super(useCaseHandler);
        mCounter = 0;
    }

    @Override
    public void attachView(MainContract.View mvpView) {
        super.attachView(mvpView);
        showCounter();
    }

    /**
     * Called by Data Binding library.
     */
    @Override
    public void onFabClick() {
        getView().showFabEvent();
        mCounter++;
        showCounter();
    }

    @Override
    public void onGoToSecondView() {
        getView().showSecondView();
    }

    @Override
    public void onGoToLongRunning() {
        getView().showLongRunningView();
    }

    private void showCounter() {
        getView().showCounter(String.format(Locale.getDefault(), "Fab pressed : %d times", mCounter));
    }
}