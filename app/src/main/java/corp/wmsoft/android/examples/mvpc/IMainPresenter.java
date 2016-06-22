package corp.wmsoft.android.examples.mvpc;

import corp.wmsoft.android.lib.mvpcandroid.presenter.IBasePresenter;

/**
 * Created by admin on 6/22/16.
 *
 */
public interface IMainPresenter extends IBasePresenter<IMainView> {

    void onMessageTap();

}
