package corp.wmsoft.android.examples.mvpc.third.view;


import corp.wmsoft.android.lib.mvpcandroid.base.IBasePresenter;
import corp.wmsoft.android.lib.mvpcandroid.base.IBaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface FrameLayoutContract {

    interface View extends IBaseView {

        void showCounter(String count);

    }

    interface Presenter extends IBasePresenter<View> {

        void onCount();

    }
}