package corp.wmsoft.android.examples.mvpc.longrunning.iteractor;

import java.util.concurrent.TimeUnit;

import corp.wmsoft.android.examples.mvpc.util.DateTimeUtil;
import corp.wmsoft.android.lib.mvpcrx.interactor.MVPCUseCase;
import corp.wmsoft.android.lib.mvpcrx.util.IMVPCSchedulerProvider;
import rx.Observable;
import rx.functions.Func1;


/**
 * Created by westman on 8/22/16.
 *
 */
public class DelayedUseCase extends MVPCUseCase<DelayedUseCase.RequestValues, String> {

    /**/
    private LongRunningUseCase mLongRunningUseCase;


    public DelayedUseCase(
            IMVPCSchedulerProvider schedulerProvider,
            LongRunningUseCase longRunningUseCase) {
        super(schedulerProvider);
        this.mLongRunningUseCase = longRunningUseCase;
    }

    @Override
    public Observable<String> buildUseCaseObservable(RequestValues requestValues) {
        return create(requestValues);
    }

    private Observable<String> create(RequestValues requestValues) {
        return mLongRunningUseCase
                .buildUseCaseObservable(requestValues.getLongRunningUseCaseRequestValues())
                .delay(3, TimeUnit.SECONDS)
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {

                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        return s + " [" + DateTimeUtil.formatMillisecondsToString(System.currentTimeMillis()) + "]";
                    }
                });
    }



    public static class RequestValues extends MVPCUseCase.RequestValues {

        /**/
        private LongRunningUseCase.RequestValues mLongRunningUseCaseRequestValues;


        public RequestValues(LongRunningUseCase.RequestValues mLongRunningUseCaseRequestValues) {
            this.mLongRunningUseCaseRequestValues = mLongRunningUseCaseRequestValues;
        }

        public LongRunningUseCase.RequestValues getLongRunningUseCaseRequestValues() {
            return mLongRunningUseCaseRequestValues;
        }
    }
}
