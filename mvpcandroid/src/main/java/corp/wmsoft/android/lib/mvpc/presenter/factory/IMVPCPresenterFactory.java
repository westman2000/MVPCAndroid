package corp.wmsoft.android.lib.mvpc.presenter.factory;

import corp.wmsoft.android.lib.mvpc.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpc.view.IMVPCView;

/**
 * Creates a Presenter object.
 * @param <V> view type
 * @param <P> presenter type
 */
public interface IMVPCPresenterFactory<V extends IMVPCView, P extends IMVPCPresenter<V>> {

    P create();

}
