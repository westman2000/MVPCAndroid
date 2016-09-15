package corp.wmsoft.android.examples.mvpc.third;

import corp.wmsoft.android.lib.mvpcrx.presenter.factory.IMVPCPresenterFactory;


/**
 * Created by admin on 8/5/16.
 *
 */
public class ThirdPresenterFactory implements IMVPCPresenterFactory<ThirdContract.View, ThirdContract.Presenter> {


    public ThirdPresenterFactory() {
    }

    @Override
    public ThirdContract.Presenter create() {
        return new ThirdPresenter();
    }
}
