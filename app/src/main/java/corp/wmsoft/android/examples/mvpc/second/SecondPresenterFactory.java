package corp.wmsoft.android.examples.mvpc.second;

import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;


/**
 * Created by admin on 8/5/16.
 *
 */
public class SecondPresenterFactory implements IMVPCPresenterFactory<SecondContract.View, SecondContract.Presenter> {


    public SecondPresenterFactory() {
    }

    @Override
    public SecondContract.Presenter create() {
        return new SecondPresenter();
    }
}