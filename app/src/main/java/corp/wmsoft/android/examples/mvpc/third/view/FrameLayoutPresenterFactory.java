package corp.wmsoft.android.examples.mvpc.third.view;


import corp.wmsoft.android.lib.mvpcandroid.base.IPresenterFactory;

/**
 * Created by admin on 8/5/16.
 *
 */
public class FrameLayoutPresenterFactory implements IPresenterFactory<FrameLayoutContract.View, FrameLayoutContract.Presenter> {


    public FrameLayoutPresenterFactory() {
    }

    @Override
    public FrameLayoutContract.Presenter create() {
        return new FrameLayoutPresenter();
    }
}
