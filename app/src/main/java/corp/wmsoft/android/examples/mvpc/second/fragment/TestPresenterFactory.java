package corp.wmsoft.android.examples.mvpc.second.fragment;

import corp.wmsoft.android.examples.mvpc.di.Injection;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;


/**
 * Created by admin on 8/5/16.
 *
 */
public class TestPresenterFactory implements IMVPCPresenterFactory<TestContract.View, TestContract.Presenter> {

    /**/
    private String mPresenterName;

    public TestPresenterFactory(String name) {
        mPresenterName = name;
    }

    @Override
    public TestContract.Presenter create() {
        return new TestPresenter(Injection.provideMVPCUseCaseHandler(), mPresenterName);
    }
}
