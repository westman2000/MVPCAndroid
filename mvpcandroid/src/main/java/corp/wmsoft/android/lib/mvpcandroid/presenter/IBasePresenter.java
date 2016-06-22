package corp.wmsoft.android.lib.mvpcandroid.presenter;

import corp.wmsoft.android.lib.mvpcandroid.view.IBaseView;


/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void detachView();
}