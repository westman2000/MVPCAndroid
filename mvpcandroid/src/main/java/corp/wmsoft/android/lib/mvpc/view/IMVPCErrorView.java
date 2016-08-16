package corp.wmsoft.android.lib.mvpc.view;

/**
 * View that can show error messages
 */
public interface IMVPCErrorView extends IMVPCView {

    void showError(Error error);

}