package corp.wmsoft.android.lib.mvpc.presenter;

import corp.wmsoft.android.lib.mvpc.view.IMVPCView;

/**
 * Every presenter in the app must either implement this interface or extend MVPCPresenter
 * indicating the MvpView type that wants to be attached with.
 * @param <V> view type
 */
public interface IMVPCPresenter<V extends IMVPCView> {

    void attachView(V view);

    boolean isViewAttached();

    void detachView();

    void onDestroyed();

}