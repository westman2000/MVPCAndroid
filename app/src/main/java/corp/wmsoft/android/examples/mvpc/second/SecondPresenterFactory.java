package corp.wmsoft.android.examples.mvpc.second;


import corp.wmsoft.android.lib.mvpcandroid.base.IPresenterFactory;

/**
 * Created by admin on 8/5/16.
 *
 */
public class SecondPresenterFactory implements IPresenterFactory<SecondContract.View, SecondContract.Presenter> {


    public SecondPresenterFactory() {
    }

    @Override
    public SecondContract.Presenter create() {
        return new SecondPresenter();
    }
}
