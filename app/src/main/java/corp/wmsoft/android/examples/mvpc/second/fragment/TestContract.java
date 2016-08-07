package corp.wmsoft.android.examples.mvpc.second.fragment;


import corp.wmsoft.android.lib.mvpcandroid.base.IBasePresenter;
import corp.wmsoft.android.lib.mvpcandroid.base.IBaseView;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface TestContract {

    interface View extends IBaseView {

        void showCounter(String counter);

        void showThirdView();

        void showCurrentViewName(String name);

        void setGoToThirdVisibility(boolean isVisible);
    }

    interface Presenter extends IBasePresenter<View> {

        void onCount();

        void onGoToThird();
    }
}