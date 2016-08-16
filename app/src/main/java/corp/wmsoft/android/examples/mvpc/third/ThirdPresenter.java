package corp.wmsoft.android.examples.mvpc.third;

import java.util.Locale;

import corp.wmsoft.android.lib.mvpc.presenter.MVPCPresenter;


/**
 * Created by admin on 8/5/16.
 *
 */
public class ThirdPresenter extends MVPCPresenter<ThirdContract.View> implements ThirdContract.Presenter {

    /**/
    private int mCounter;


    public ThirdPresenter() {
        mCounter = 0;
    }

    @Override
    public void attachView(ThirdContract.View mvpView) {
        super.attachView(mvpView);
        showCounter();
    }

    /**
     * Called by Data Binding library.
     */
    @Override
    public void onFabClick() {
        getView().showFabEvent();
    }

    /**
     * Called by Data Binding library.
     */
    @Override
    public void onCount() {
        mCounter++;
        showCounter();
    }
    private void showCounter() {
        getView().showCounter(String.format(Locale.getDefault(), "Fab pressed : %d times", mCounter));
    }

}