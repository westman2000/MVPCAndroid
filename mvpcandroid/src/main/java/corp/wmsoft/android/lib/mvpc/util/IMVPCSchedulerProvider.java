package corp.wmsoft.android.lib.mvpc.util;

import rx.Scheduler;

/**
 * Allow providing different types of {@link Scheduler}s.
 */
public interface IMVPCSchedulerProvider {

    Scheduler io();

    Scheduler ui();
}
