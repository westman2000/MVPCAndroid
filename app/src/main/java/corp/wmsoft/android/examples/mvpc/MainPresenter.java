package corp.wmsoft.android.examples.mvpc;

import corp.wmsoft.android.lib.mvpcandroid.presenter.BasePresenter;

/**
 * Created by admin on 6/22/16.
 *
 */
public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {

    private boolean isTest1Visible;


    @Override
    public void attachView(IMainView mvpView) {
        super.attachView(mvpView);

        getView().showMessage("hello world from presenter");

        isTest1Visible = true;

        getView().setTest1Visibility(isTest1Visible);
    }

    @Override
    public void onMessageTap() {
        isTest1Visible = !isTest1Visible;
        getView().setTest1Visibility(isTest1Visible);
    }
}
