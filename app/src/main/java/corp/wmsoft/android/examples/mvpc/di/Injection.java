package corp.wmsoft.android.examples.mvpc.di;

import corp.wmsoft.android.examples.mvpc.longrunning.iteractor.LongRunningUseCase;
import corp.wmsoft.android.lib.mvpc.interactor.MVPCUseCaseHandler;

/**
 * Created by westman on 8/22/16.
 */
public class Injection {

    /***********************************************************
     * UseCases
     */
    public static MVPCUseCaseHandler provideMVPCUseCaseHandler() {
        return MVPCUseCaseHandler.getInstance();
    }

    public static LongRunningUseCase provideLongRunningUseCase() {
        return new LongRunningUseCase();
    }

}
