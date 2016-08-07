package corp.wmsoft.android.examples.mvpc;


import corp.wmsoft.android.lib.mvpcandroid.base.IPresenterFactory;

/**
 * Created by admin on 8/5/16.
 *
 */
public class MainPresenterFactory implements IPresenterFactory<MainContract.View, MainContract.Presenter> {


    public MainPresenterFactory() {
    }

    @Override
    public MainContract.Presenter create() {
        return new MainPresenter();
    }
}
