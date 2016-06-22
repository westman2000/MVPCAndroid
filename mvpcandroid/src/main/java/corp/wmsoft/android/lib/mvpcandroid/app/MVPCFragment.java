package corp.wmsoft.android.lib.mvpcandroid.app;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.Menu;

import corp.wmsoft.android.lib.mvpcandroid.exceptions.MvpViewNotImplementedException;
import corp.wmsoft.android.lib.mvpcandroid.presenter.IBasePresenter;
import corp.wmsoft.android.lib.mvpcandroid.view.IBaseView;


/**
 * Fragment that uses an {@link IBasePresenter} to implement a Model-View-Presenter architecture.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class MVPCFragment<V extends IBaseView, P extends IBasePresenter<V>> extends Fragment {

    /**/
    private P mPresenter;


    public MVPCFragment() {
        if (!(this instanceof IBaseView))
            throw new MvpViewNotImplementedException();
    }

    /**
     *
     * @return presenter instance
     */
    protected abstract @NonNull P providePresenter();

    @CallSuper
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = providePresenter();
        setHasOptionsMenu(true);
    }

    @CallSuper
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        //noinspection unchecked
        mPresenter.attachView((V)this);
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    protected @NonNull P getPresenter() {
        return mPresenter;
    }
}