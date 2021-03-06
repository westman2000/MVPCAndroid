package corp.wmsoft.android.examples.mvpc.longrunning.iteractor;

import corp.wmsoft.android.examples.mvpc.util.DateTimeUtil;
import corp.wmsoft.android.lib.mvpcrx.interactor.MVPCUseCase;
import corp.wmsoft.android.lib.mvpcrx.util.IMVPCSchedulerProvider;
import rx.Observable;
import rx.Subscriber;


/**
 * Created by westman on 8/22/16.
 *
 */
public class LongRunningUseCase extends MVPCUseCase<LongRunningUseCase.RequestValues, String> {


    public LongRunningUseCase(IMVPCSchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }

    @Override
    public Observable<String> buildUseCaseObservable(RequestValues requestValues) {
        return fromFake();
    }

    private Observable<String> fromFake() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    int i = 0;
                    while (i < 14) {
                        subscriber.onNext(i+" at "+ DateTimeUtil.formatMillisecondsToString(System.currentTimeMillis()));
                        i++;
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    public static class RequestValues extends MVPCUseCase.RequestValues {}

}
