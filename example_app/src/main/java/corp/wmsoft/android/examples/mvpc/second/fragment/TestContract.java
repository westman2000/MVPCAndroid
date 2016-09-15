package corp.wmsoft.android.examples.mvpc.second.fragment;

import corp.wmsoft.android.lib.mvpcrx.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcrx.view.IMVPCView;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface TestContract {

    interface View extends IMVPCView {

        void showCounter(String counter);

        void showThirdView();

        void showCurrentViewName(String name);

        void setGoToThirdVisibility(boolean isVisible);
    }

    interface Presenter extends IMVPCPresenter<View> {

        void onCount();

        void onGoToThird();
    }
}