package corp.wmsoft.android.examples.mvpc.third;


import corp.wmsoft.android.lib.mvpcandroid.base.IPresenterFactory;

/**
 * Created by admin on 8/5/16.
 *
 */
public class ThirdPresenterFactory implements IPresenterFactory<ThirdContract.View, ThirdContract.Presenter> {


    public ThirdPresenterFactory() {
    }

    @Override
    public ThirdContract.Presenter create() {
        return new ThirdPresenter();
    }
}
