package corp.wmsoft.android.lib.mvpcandroid.exceptions;

/**
 * Created by admin on 6/22/16.
 *
 */
public class MVPCViewNotImplementedException extends RuntimeException {

    public MVPCViewNotImplementedException() {
        super("Class must implement IMVPCView");
    }

}