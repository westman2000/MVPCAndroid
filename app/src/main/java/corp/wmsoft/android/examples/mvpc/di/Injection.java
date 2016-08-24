package corp.wmsoft.android.examples.mvpc.di;

import corp.wmsoft.android.examples.mvpc.longrunning.iteractor.LongRunningUseCase;
import corp.wmsoft.android.lib.mvpc.util.IMVPCSchedulerProvider;
import corp.wmsoft.android.lib.mvpc.util.MVPCSchedulerProvider;

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

}
