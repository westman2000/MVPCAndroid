package corp.wmsoft.android.examples.mvpc.longrunning.iteractor;

import java.util.concurrent.TimeUnit;

import corp.wmsoft.android.examples.mvpc.util.DateTimeUtil;
import corp.wmsoft.android.lib.mvpc.interactor.MVPCUseCase;
import corp.wmsoft.android.lib.mvpc.util.IMVPCSchedulerProvider;
import rx.Observable;
import rx.functions.Func1;


/**
 * Created by westman on 8/22/16.
 *
 */
public class DelayedUseCase extends MVPCUseCase<String> {

    /**/
    private LongRunningUseCase mLongRunningUseCase;


    public DelayedUseCase(
            IMVPCSchedulerProvider schedulerProvider,
            LongRunningUseCase longRunningUseCase) {
        super(schedulerProvider);
        this.mLongRunningUseCase = longRunningUseCase;
    }

    @Override
    public Observable<String> buildUseCaseObservable() {
        return create();
    }

    private Observable<String> create() {
        return mLongRunningUseCase
                .buildUseCaseObservable()
                .delay(3, TimeUnit.SECONDS)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + " [" + DateTimeUtil.formatMillisecondsToString(System.currentTimeMillis()) + "]";
                    }
                });
    }

}
