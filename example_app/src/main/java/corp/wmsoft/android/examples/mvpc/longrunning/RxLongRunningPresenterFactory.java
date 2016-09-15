package corp.wmsoft.android.examples.mvpc.longrunning;

import corp.wmsoft.android.examples.mvpc.di.Injection;
import corp.wmsoft.android.lib.mvpcrx.presenter.factory.IMVPCPresenterFactory;


/**
 * Created by admin on 8/5/16.
 *
 */
public class RxLongRunningPresenterFactory implements IMVPCPresenterFactory<LongRunningContract.View, LongRunningContract.Presenter> {


    public RxLongRunningPresenterFactory() {
    }

    @Override
    public LongRunningContract.Presenter create() {
        return new RxLongRunningPresenter(Injection.provideDelayedUseCase());
    }
}