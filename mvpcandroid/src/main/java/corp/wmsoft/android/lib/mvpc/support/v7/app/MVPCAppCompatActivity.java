package corp.wmsoft.android.lib.mvpc.support.v7.app;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import corp.wmsoft.android.lib.mvpc.delegate.MVPCDelegate;
import corp.wmsoft.android.lib.mvpc.exceptions.MVPCViewNotImplementedException;
import corp.wmsoft.android.lib.mvpc.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpc.support.v4.content.MVPCPresenterLoader;
import corp.wmsoft.android.lib.mvpc.view.IMVPCView;


/**
 * Created by admin on 8/5/16.
 * @deprecated will redesign with {@link MVPCDelegate}
 */
@Deprecated
public abstract class MVPCAppCompatActivity<V extends IMVPCView, P extends IMVPCPresenter<V>> extends AppCompatActivity  {

    /**/
    private static final int LOADER_ID = 666;

    /**/
    private P mPresenter;


    /**/
    protected abstract IMVPCPresenterFactory<V, P> providePresenterFactory();

    /**/
    protected abstract void onInitializePresenter(P presenter);


    public MVPCAppCompatActivity() {
        if (!(this instanceof IMVPCView))
            throw new MVPCViewNotImplementedException();
    }

    /**/
    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<P>() {

            @Override
            public Loader<P> onCreateLoader(int i, Bundle bundle) {
                return new MVPCPresenterLoader<>(MVPCAppCompatActivity.this, providePresenterFactory());
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P presenter) {
                setPresenter(presenter);
                onInitializePresenter(presenter);
            }

            @Override
            public void onLoaderReset(Loader<P> loader) {
                onDestroyPresenter();
                setPresenter(null);
            }
        });
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null && !mPresenter.isViewAttached())
            //noinspection unchecked
            mPresenter.attachView((V) this);
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null && mPresenter.isViewAttached())
            mPresenter.detachView();
    }

    @CallSuper
    protected void onDestroyPresenter() {
        // hook for subclasses
        if (mPresenter != null && mPresenter.isViewAttached())
            mPresenter.detachView();
    }

    protected P getPresenter() {
        if (mPresenter == null)
            throw new NullPointerException("Please wait before requesting Presenter");
        return mPresenter;
    }

    private void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }
}
