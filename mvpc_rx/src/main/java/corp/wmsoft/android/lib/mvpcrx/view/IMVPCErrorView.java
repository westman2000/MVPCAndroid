package corp.wmsoft.android.lib.mvpcrx.view;

/**
 * View that can show error messages
 */
public interface IMVPCErrorView extends IMVPCView {

    void showError(Error error);

}