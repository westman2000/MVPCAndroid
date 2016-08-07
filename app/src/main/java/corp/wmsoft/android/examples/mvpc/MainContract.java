package corp.wmsoft.android.examples.mvpc;


import corp.wmsoft.android.lib.mvpcandroid.base.IBasePresenter;
import corp.wmsoft.android.lib.mvpcandroid.base.IBaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface MainContract {

    interface View extends IBaseView {

        void showFabEvent();

        void showCounter(String count);

        void showSecondView();

    }

    interface Presenter extends IBasePresenter<View> {

        void onFabClick();

        void onGoToSecondView();

    }
}