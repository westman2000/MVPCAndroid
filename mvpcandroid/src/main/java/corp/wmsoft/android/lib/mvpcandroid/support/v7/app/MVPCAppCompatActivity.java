package corp.wmsoft.android.lib.mvpcandroid.support.v7.app;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import corp.wmsoft.android.lib.mvpcandroid.exceptions.MvpViewNotImplementedException;
import corp.wmsoft.android.lib.mvpcandroid.presenter.IBasePresenter;
import corp.wmsoft.android.lib.mvpcandroid.view.IBaseView;


/**
 * An Activity that uses an {@link IBasePresenter} to implement a Model-View-Presenter architecture.
 */
@Deprecated
public abstract class MVPCAppCompatActivity<V extends IBaseView, P extends IBasePresenter<V>> extends AppCompatActivity {

    /**/
    private P mPresenter;


    public MVPCAppCompatActivity() {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = providePresenter();
    }

    @CallSuper
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //noinspection unchecked
        mPresenter.attachView((V)this);
        return super.onPrepareOptionsMenu(menu);
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.detachView();
    }

    protected @NonNull P getPresenter() {
        return mPresenter;
    }

}