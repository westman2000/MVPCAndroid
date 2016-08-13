package corp.wmsoft.android.examples.mvpc;

import corp.wmsoft.android.lib.mvpcandroid.presenter.factory.IMVPCPresenterFactory;


/**
 * Created by admin on 8/5/16.
 *
 */
public class MainPresenterFactory implements IMVPCPresenterFactory<MainContract.View, MainContract.Presenter> {


    public MainPresenterFactory() {
    }

    @Override
    public MainContract.Presenter create() {
        return new MainPresenter();
    }
}
