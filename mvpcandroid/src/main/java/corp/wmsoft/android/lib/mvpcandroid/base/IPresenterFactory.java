package corp.wmsoft.android.lib.mvpcandroid.base;

/**
 * Creates a Presenter object.
 * @param <V> view type
 * @param <P> presenter type
 */
public interface IPresenterFactory<V extends IBaseView, P extends IBasePresenter<V>> {

    P create();

}
