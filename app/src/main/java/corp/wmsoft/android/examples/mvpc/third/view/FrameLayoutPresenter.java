package corp.wmsoft.android.examples.mvpc.third.view;

import java.util.Locale;

import corp.wmsoft.android.lib.mvpc.presenter.MVPCPresenter;


/**
 * Created by admin on 8/5/16.
 *
 */
public class FrameLayoutPresenter extends MVPCPresenter<FrameLayoutContract.View> implements FrameLayoutContract.Presenter {

    /**/
    private int mCounter;


    public FrameLayoutPresenter() {
        mCounter = 0;
    }

    @Override
    public void attachView(FrameLayoutContract.View mvpView) {
        super.attachView(mvpView);
        showCounter();
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
        getView().showCounter(String.format(Locale.getDefault(), "Counter is : %d", mCounter));
    }

}