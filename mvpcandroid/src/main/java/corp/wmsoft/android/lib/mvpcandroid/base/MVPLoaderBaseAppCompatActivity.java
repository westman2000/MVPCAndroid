package corp.wmsoft.android.lib.mvpcandroid.base;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by admin on 8/5/16.
 *
 */
public abstract class MVPLoaderBaseAppCompatActivity<V extends IBaseView, P extends IBasePresenter<V>> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<P> {

    /**/
    private static final int LOADER_ID = 666;

    /**/
    private P mPresenter;


    /**/
    protected abstract IPresenterFactory<V, P> providePresenterFactory();

    /**/
    protected abstract void onInitializePresenter(P presenter);

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
        return new PresenterLoader<>(this, providePresenterFactory());
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
