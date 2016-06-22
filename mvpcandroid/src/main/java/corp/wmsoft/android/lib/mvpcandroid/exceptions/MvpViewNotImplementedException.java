package corp.wmsoft.android.lib.mvpcandroid.exceptions;

/**
 * Created by admin on 6/22/16.
 *
 */
public class MvpViewNotImplementedException extends RuntimeException {

    public MvpViewNotImplementedException() {
        super("Class must implement IBaseView");
    }

}
