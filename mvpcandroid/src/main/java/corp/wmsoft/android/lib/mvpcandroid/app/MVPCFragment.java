package corp.wmsoft.android.lib.mvpcandroid.app;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.CallSuper;

import corp.wmsoft.android.lib.mvpcandroid.exceptions.MVPCViewNotImplementedException;
import corp.wmsoft.android.lib.mvpcandroid.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcandroid.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpcandroid.presenter.loader.MVPCPresenterLoader;
import corp.wmsoft.android.lib.mvpcandroid.view.IMVPCView;


/**
 * Created by westman on 8/5/16.
 *
 */
public abstract class MVPCFragment<V extends IMVPCView, P extends IMVPCPresenter<V>> extends Fragment implements LoaderManager.LoaderCallbacks<P> {

    /**/
    private static final int LOADER_ID = 666;

    /**/
    private P mPresenter;

    // boolean flag to avoid delivering the result twice. Calling initLoader in onActivityCreated makes
    // onLoadFinished will be called twice during configuration change.
    // Do not use this because of child fragment(have saved presenter) in viewpager but data binding do not receive onInitializePresenter
    private boolean isDelivered = false;



    /**/
    protected abstract IMVPCPresenterFactory<V, P> providePresenterFactory();

    /**/
    protected abstract void onInitializePresenter(P presenter);


    public MVPCFragment() {
        if (!(this instanceof IMVPCView))
            throw new MVPCViewNotImplementedException();
    }

    @CallSuper
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        getPresenter().attachView((V)this);
    }

    @CallSuper
    @Override
    public void onPause() {
        getPresenter().detachView();
        super.onPause();
    }

    @Override
    public Loader<P> onCreateLoader(int i, Bundle bundle) {
        return new MVPCPresenterLoader<>(getActivity(), providePresenterFactory());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P presenter) {
//        if (!isDelivered) {
//            isDelivered = true;
            this.mPresenter = presenter;
            onInitializePresenter(presenter);
//        }
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
