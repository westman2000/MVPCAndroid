package corp.wmsoft.android.examples.mvpc.second;

import corp.wmsoft.android.lib.mvpcrx.presenter.MVPCPresenter;


/**
 * Created by admin on 8/5/16.
 *
 */
public class SecondPresenter extends MVPCPresenter<SecondContract.View> implements SecondContract.Presenter {


    public SecondPresenter() {
    }

    /**
     * Called by Data Binding library.
     */
    @Override
    public void onFabClick() {
        getView().showFabEvent();
    }
}