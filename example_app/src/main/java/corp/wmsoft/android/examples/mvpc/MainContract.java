package corp.wmsoft.android.examples.mvpc;

import corp.wmsoft.android.lib.mvpcrx.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcrx.view.IMVPCView;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface MainContract {

    interface View extends IMVPCView {

        void showFabEvent();

        void showCounter(String count);

        void showSecondView();

        void showLongRunningView();

    }

    interface Presenter extends IMVPCPresenter<View> {

        void onFabClick();

        void onGoToSecondView();

        void onGoToLongRunning();

    }
}