package corp.wmsoft.android.lib.mvpcandroid.view;


/**
 * View which data that need to be prepared/downloaded before show to user
 */
@Deprecated
public interface IDelayedDataView<D> extends IErrorView {

    void showLoading();

    void hideLoading();

    void setData(D data);

    void showContent();

    void showNoDataView();

}