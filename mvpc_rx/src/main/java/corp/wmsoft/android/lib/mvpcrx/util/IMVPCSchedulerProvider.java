package corp.wmsoft.android.lib.mvpcrx.util;

import rx.Scheduler;

/**
 * Allow providing different types of {@link Scheduler}s.
 */
public interface IMVPCSchedulerProvider {

    Scheduler io();

    Scheduler ui();
}
