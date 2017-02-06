package corp.wmsoft.android.examples.mvpc;

import java.util.Locale;

import corp.wmsoft.android.lib.mvpcrx.presenter.MVPCPresenter;
import corp.wmsoft.android.lib.mvpcrx.presenter.factory.IMVPCPresenterFactory;


/**
 * Created by admin on 8/5/16.
 *
 */
class MainPresenter extends MVPCPresenter<MainContract.View> implements MainContract.Presenter, IMVPCPresenterFactory<MainContract.View, MainContract.Presenter> {

    /**/
    private int mCounter;


    MainPresenter() {
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

    @Override
    public MainContract.Presenter create() {
        return this;
    }
}