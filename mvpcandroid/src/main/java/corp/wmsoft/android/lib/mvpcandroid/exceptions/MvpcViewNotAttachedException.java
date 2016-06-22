package corp.wmsoft.android.lib.mvpcandroid.exceptions;

/**
 * Created by admin on 6/22/16.
 *
 */
public class MvpcViewNotAttachedException extends RuntimeException {

    public MvpcViewNotAttachedException() {
        super("Please call Presenter.attachView(IBaseView) before requesting data to the Presenter");
    }

}