package corp.wmsoft.android.lib.mvpcandroid.view;

/**
 * View that can show error messages
 */
@Deprecated
public interface IErrorView extends IBaseView {

    void showError(Error error);

}