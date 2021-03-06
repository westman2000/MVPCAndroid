package corp.wmsoft.android.examples.mvpc.longrunning;

import corp.wmsoft.android.lib.mvpcrx.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcrx.view.IMVPCView;


/**
 * This specifies the contract between the view and the presenter.
 */
public interface LongRunningContract {

    interface View extends IMVPCView {

        void showLoading();

        void hideLoading();

        void showMessage(String message);

    }

    interface Presenter extends IMVPCPresenter<View> {

        void onStartUseCase();

    }
}