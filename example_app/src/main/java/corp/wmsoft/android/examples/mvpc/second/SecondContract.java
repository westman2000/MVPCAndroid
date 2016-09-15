package corp.wmsoft.android.examples.mvpc.second;

import corp.wmsoft.android.lib.mvpcrx.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcrx.view.IMVPCView;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface SecondContract {

    interface View extends IMVPCView {

        void showFabEvent();

    }

    interface Presenter extends IMVPCPresenter<View> {

        void onFabClick();

    }
}