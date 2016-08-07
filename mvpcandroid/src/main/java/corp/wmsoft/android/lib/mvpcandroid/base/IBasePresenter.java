package corp.wmsoft.android.lib.mvpcandroid.base;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 * @param <V> view type
 */
public interface IBasePresenter<V extends IBaseView> {

    void attachView(V view);

    void detachView();

    void onDestroyed();

}