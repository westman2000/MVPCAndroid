package corp.wmsoft.android.lib.mvpc.util;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Provides different types of schedulers.
 */
public class MVPCSchedulerProvider implements IMVPCSchedulerProvider {

    /**/
    private static MVPCSchedulerProvider INSTANCE;


    // Prevent direct instantiation.
    private MVPCSchedulerProvider() {
    }

    public static MVPCSchedulerProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MVPCSchedulerProvider();
        }
        return INSTANCE;
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}