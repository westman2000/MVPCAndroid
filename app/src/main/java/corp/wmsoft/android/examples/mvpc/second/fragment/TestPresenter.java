package corp.wmsoft.android.examples.mvpc.second.fragment;

import java.util.Locale;

import corp.wmsoft.android.lib.mvpcandroid.base.BasePresenter;


/**
 * Created by admin on 8/5/16.
 *
 */
public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    /**/
    private String mName;
    /**/
    private int mCounter;


    public TestPresenter(String name) {
        mName = name;
        mCounter = 0;
    }

    @Override
    public void attachView(TestContract.View mvpView) {
        super.attachView(mvpView);
        showCounter();
        getView().showCurrentViewName(mName == null ? "view without name" : mName);
        getView().setGoToThirdVisibility(mName == null);
    }

    @Override
    public void onCount() {
        mCounter++;
        showCounter();
    }

    @Override
    public void onGoToThird() {
        getView().showThirdView();
    }

    private void showCounter() {
        getView().showCounter(String.format(Locale.getDefault(), "Fab pressed : %d times", mCounter));
    }
}