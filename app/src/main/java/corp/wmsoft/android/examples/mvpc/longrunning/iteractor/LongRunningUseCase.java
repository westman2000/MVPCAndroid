package corp.wmsoft.android.examples.mvpc.longrunning.iteractor;

import java.util.concurrent.TimeUnit;

import corp.wmsoft.android.lib.mvpc.interactor.MVPCUseCase;
import corp.wmsoft.android.lib.mvpc.util.IMVPCSchedulerProvider;
import rx.Observable;
import rx.Subscriber;


/**
 * Created by westman on 8/22/16.
 *
 */
public class LongRunningUseCase extends MVPCUseCase<String> {


    public LongRunningUseCase(IMVPCSchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }

    @Override
    protected Observable<String> buildUseCaseObservable() {
        return fromFake();
    }

    private Observable<String> fromFake() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    int i = 0;
                    while (i < 14) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            subscriber.onError(new Error("was error"));
                        }
                        subscriber.onNext("observable on next "+i);
                        i++;
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

}
