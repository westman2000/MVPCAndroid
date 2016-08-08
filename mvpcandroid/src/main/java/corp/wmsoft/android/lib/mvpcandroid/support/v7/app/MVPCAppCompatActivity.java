package corp.wmsoft.android.lib.mvpcandroid.support.v7.app;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import corp.wmsoft.android.lib.mvpcandroid.exceptions.MVPCViewNotImplementedException;
import corp.wmsoft.android.lib.mvpcandroid.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcandroid.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpcandroid.presenter.loader.MVPCPresenterLoader;
import corp.wmsoft.android.lib.mvpcandroid.view.IMVPCView;


/**
 * Created by admin on 8/5/16.
 *
 */
public abstract class MVPCAppCompatActivity<V extends IMVPCView, P extends IMVPCPresenter<V>> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<P> {

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
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onPostResume() {
        getPresenter().attachView((V) this);
        super.onPostResume();
    }

    @CallSuper
    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().detachView();
    }

    @Override
    public Loader<P> onCreateLoader(int i, Bundle bundle) {
        return new MVPCPresenterLoader<>(this, providePresenterFactory());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P presenter) {
        this.mPresenter = presenter;
        onInitializePresenter(presenter);
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        this.mPresenter = null;
    }

    protected P getPresenter() {
        if (mPresenter == null)
            throw new NullPointerException("Please wait before requesting Presenter");
        return mPresenter;
    }
}
