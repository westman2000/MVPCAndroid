package corp.wmsoft.android.lib.mvpcrx.exceptions;

/**
 * Created by admin on 6/22/16.
 *
 */
public class MVPCViewNotAttachedException extends RuntimeException {

    public MVPCViewNotAttachedException() {
        super("Please call Presenter.attachView(IMVPCView) before requesting data to the Presenter");
    }

}
