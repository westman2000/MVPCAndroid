package corp.wmsoft.android.examples.mvpc.third.view;

import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;


/**
 * Created by admin on 8/5/16.
 *
 */
public class FrameLayoutPresenterFactory implements IMVPCPresenterFactory<FrameLayoutContract.View, FrameLayoutContract.Presenter> {


    public FrameLayoutPresenterFactory() {
    }

    @Override
    public FrameLayoutContract.Presenter create() {
        return new FrameLayoutPresenter();
    }
}
