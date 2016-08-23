package corp.wmsoft.android.examples.mvpc.longrunning;

import corp.wmsoft.android.examples.mvpc.di.Injection;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;


/**
 * Created by admin on 8/5/16.
 *
 */
public class LongRunningPresenterFactory implements IMVPCPresenterFactory<LongRunningContract.View, LongRunningContract.Presenter> {


    public LongRunningPresenterFactory() {
    }

    @Override
    public LongRunningContract.Presenter create() {
        return new LongRunningPresenter(
                Injection.provideMVPCUseCaseHandler(),
                Injection.provideLongRunningUseCase()
        );
    }
}