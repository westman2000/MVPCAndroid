package corp.wmsoft.android.examples.mvpc.di;

import corp.wmsoft.android.examples.mvpc.longrunning.iteractor.DelayedUseCase;
import corp.wmsoft.android.examples.mvpc.longrunning.iteractor.LongRunningUseCase;
import corp.wmsoft.android.lib.mvpcrx.util.IMVPCSchedulerProvider;
import corp.wmsoft.android.lib.mvpcrx.util.MVPCSchedulerProvider;

/**
 * Created by westman on 8/22/16.
 *
 */
public class Injection {

    /***********************************************************
     * UseCases
     */
    public static IMVPCSchedulerProvider provideMVPCSchedulerProvider() {
        return MVPCSchedulerProvider.getInstance();
    }

    public static LongRunningUseCase provideLongRunningUseCase() {
        return new LongRunningUseCase(provideMVPCSchedulerProvider());
    }

    public static DelayedUseCase provideDelayedUseCase() {
        return new DelayedUseCase(provideMVPCSchedulerProvider(), provideLongRunningUseCase());
    }

}
