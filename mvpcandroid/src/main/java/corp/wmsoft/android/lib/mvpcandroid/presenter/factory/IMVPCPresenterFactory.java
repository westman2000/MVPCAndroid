package corp.wmsoft.android.lib.mvpcandroid.presenter.factory;

import corp.wmsoft.android.lib.mvpcandroid.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcandroid.view.IMVPCView;

/**
 * Creates a Presenter object.
 * @param <V> view type
 * @param <P> presenter type
 */
public interface IMVPCPresenterFactory<V extends IMVPCView, P extends IMVPCPresenter<V>> {

    P create();

}
