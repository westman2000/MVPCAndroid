package corp.wmsoft.android.lib.mvpc.exceptions;

/**
 * Created by admin on 6/22/16.
 *
 */
public class MVPCUseCaseHandlerNotSetException extends RuntimeException {

    public MVPCUseCaseHandlerNotSetException() {
        super("This presenter can't work with use case handler, set it in constructor first,");
    }

}
