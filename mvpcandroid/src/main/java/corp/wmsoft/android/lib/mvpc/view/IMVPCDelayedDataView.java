package corp.wmsoft.android.lib.mvpc.view;


/**
 * View which data that need to be prepared/downloaded before show to user
 */
public interface IMVPCDelayedDataView<D> extends IMVPCErrorView {

    void showLoading();

    void hideLoading();

    void setData(D data);

    void showContent();

    void showNoDataView();

}