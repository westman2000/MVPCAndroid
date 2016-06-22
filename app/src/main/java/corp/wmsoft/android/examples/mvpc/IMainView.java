package corp.wmsoft.android.examples.mvpc;

import corp.wmsoft.android.lib.mvpcandroid.view.IBaseView;

/**
 * Created by admin on 6/22/16.
 *
 */
public interface IMainView extends IBaseView {

    void showMessage(String message);

    void setTest1Visibility(boolean isVisible);
}
