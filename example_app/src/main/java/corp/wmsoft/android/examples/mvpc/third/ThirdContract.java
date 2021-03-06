package corp.wmsoft.android.examples.mvpc.third;

import corp.wmsoft.android.lib.mvpcrx.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcrx.view.IMVPCView;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface ThirdContract {

    interface View extends IMVPCView {

        void showFabEvent();

        void showCounter(String count);

    }

    interface Presenter extends IMVPCPresenter<View> {

        void onFabClick();

        void onCount();
    }
}