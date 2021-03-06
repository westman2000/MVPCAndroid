package corp.wmsoft.android.examples.mvpc.third.view;

import corp.wmsoft.android.lib.mvpcrx.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcrx.view.IMVPCView;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface FrameLayoutContract {

    interface View extends IMVPCView {

        void showCounter(String count);

    }

    interface Presenter extends IMVPCPresenter<View> {

        void onCount();

    }
}