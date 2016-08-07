package corp.wmsoft.android.examples.mvpc.second;


import corp.wmsoft.android.lib.mvpcandroid.base.IBasePresenter;
import corp.wmsoft.android.lib.mvpcandroid.base.IBaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface SecondContract {

    interface View extends IBaseView {

        void showFabEvent();

    }

    interface Presenter extends IBasePresenter<View> {

        void onFabClick();

    }
}