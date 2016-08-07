package corp.wmsoft.android.examples.mvpc;

import android.util.Log;

import java.util.Locale;

import corp.wmsoft.android.lib.mvpcandroid.base.BasePresenter;


/**
 * Created by admin on 8/5/16.
 *
 */
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    /**/
    private int mCounter;

    public MainPresenter() {
        Log.d("life_cycle", "MainPresenter.MainPresenter");
        mCounter = 0;
    }

    @Override
    public void attachView(MainContract.View mvpView) {
        Log.d("life_cycle", "MainPresenter.attachView");
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

    private void showCounter() {
        getView().showCounter(String.format(Locale.getDefault(), "Fab pressed : %d times", mCounter));
    }
}