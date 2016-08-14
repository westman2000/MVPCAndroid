package corp.wmsoft.android.lib.mvpcandroid.support.v4.app;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import corp.wmsoft.android.lib.mvpcandroid.exceptions.MVPCViewNotImplementedException;
import corp.wmsoft.android.lib.mvpcandroid.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcandroid.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpcandroid.support.v4.content.MVPCPresenterLoader;
import corp.wmsoft.android.lib.mvpcandroid.view.IMVPCView;


/**
 * Created by westman on 8/5/16.
 *
 */
public abstract class MVPCFragment<V extends IMVPCView, P extends IMVPCPresenter<V>> extends Fragment {

    /**/
    private static final int LOADER_ID = 666;

    /**/
    private P mPresenter;

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
        getLoaderManager().initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<P>() {

            @Override
            public Loader<P> onCreateLoader(int i, Bundle bundle) {
                return new MVPCPresenterLoader<>(getActivity(), providePresenterFactory());
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P presenter) {
                // Calling initLoader in onActivityCreated makes onLoadFinished will be called twice during configuration change.
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
    public void onStart() {
        super.onStart();
        if (mPresenter != null)
            //noinspection unchecked
            mPresenter.attachView((V) this);
    }

    /**
     * There is a important difference in regard to when Presenter will be delivered to Activities
     * or Fragments. In Activities, after calling super.onStart the Presenter will be ready to use
     * in every circumstance. However, in Fragments when first created, the Presenter will be
     * deliver after super.onStart, but on recreation it will be delivered after super.onResume.
     * So, on Fragments we can just rely that our Presenter will be there after super.onResume.
     */
    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null && !mPresenter.isViewAttached())
            //noinspection unchecked
            mPresenter.attachView((V) this);
    }

    @CallSuper
    @Override
    public void onStop() {
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