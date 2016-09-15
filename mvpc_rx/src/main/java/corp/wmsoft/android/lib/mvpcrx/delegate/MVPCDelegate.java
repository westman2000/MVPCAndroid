package corp.wmsoft.android.lib.mvpcrx.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.lang.ref.WeakReference;

import corp.wmsoft.android.lib.mvpcrx.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcrx.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpcrx.presenter.loader.MVPCPresenterLoader;
import corp.wmsoft.android.lib.mvpcrx.view.IMVPCView;


/**
 * Created on 8/18/16 at 11:21 AM.
 *
 * <p>
 * This class represents a delegate which you can use to extend Mvp's support to any class.
 * <p>
 * When using an {@link MVPCDelegate}, lifecycle methods which should be proxied to the delegate:
 * <ul>
 * <li>{@link #onCreate(Context, LoaderManager, IMVPCPresenterFactory, int, ICallback)} inside onCreate() for Activity, inside onActivityCreated() for Fragments or inside view constructor</li>
 * <li>{@link #onAttachView(V, ICallback)}: inside onStart() and onResume() of Fragment, inside onStart() of Activity, inside onAttachedToWindow for View</li>
 * <li>{@link #onDetachView()}: inside onStop() for Activity, inside onStop() for Fragment, inside onDetachedFromWindow() for View</li>
 * </ul>
 * <p>
 * Every {@link Object} can only be linked with one {@link MVPCDelegate} instance
 *
 * @author westman2000
 */
public class MVPCDelegate<V extends IMVPCView, P extends IMVPCPresenter<V>> implements IMVPCDelegate<V, P>, LoaderManager.LoaderCallbacks<P> {

    /**/
    private P                           mPresenter;
    /**/
    private WeakReference<Context>      mWeakContext;
    /**/
    private IMVPCPresenterFactory<V, P> mPresenterFactory;
    /**/
    private ICallback<V, P>             mCallback;


    /**
     *
     * @param context {@link Context}
     * @param supportLoaderManager {@link LoaderManager}
     * @param presenterFactory instance of {@link IMVPCPresenterFactory}
     * @param uniqueIdentifier application globally identifier for {@link android.view.View}'s and context identifier for {@link android.app.Activity} and {@link android.support.v4.app.Fragment}
     * @param callback {@link IMVPCDelegate.ICallback} if you want to receive callbacks of presenter lifecycle
     */
    @Override
    public void onCreate(Context context, LoaderManager supportLoaderManager, IMVPCPresenterFactory<V, P> presenterFactory, int uniqueIdentifier, ICallback<V, P> callback) {
        this.mWeakContext = new WeakReference<>(context);
        this.mPresenterFactory = presenterFactory;
        this.mCallback = callback;
        supportLoaderManager.initLoader(uniqueIdentifier, null, this);
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        if (mWeakContext != null && mWeakContext.get() != null)
            return new MVPCPresenterLoader<>(mWeakContext.get(), mPresenterFactory);
        return null;
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P presenter) {
        setPresenter(presenter);
        if (mCallback != null)
            mCallback.onInitializePresenter(presenter);
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        onDestroy();
    }

    /**
     *
     * @param view view that implements {@link IMVPCView}
     * @param callback {@link IMVPCDelegate.ICallback} if you want to receive callbacks of presenter lifecycle. must be set again after {@link MVPCDelegate#onDetachView()}
     */
    @Override
    public void onAttachView(V view, ICallback<V, P> callback) {
        if (mPresenter != null && !mPresenter.isViewAttached())
            mPresenter.attachView(view);
        this.mCallback = callback;
    }

    @Override
    public void onDetachView() {
        detachViewIfNeeded();
    }

    @Override
    public P getPresenter() {
        if (mPresenter == null)
            throw new NullPointerException("Presenter not ready. Please wait before requesting Presenter");
        return mPresenter;
    }

    private void onDestroy() {
        if (mCallback != null)
            mCallback.onDestroyPresenter();
        detachViewIfNeeded();
        setPresenter(null);
    }

    private void detachViewIfNeeded() {
        if (mPresenter != null && mPresenter.isViewAttached())
            mPresenter.detachView();
        clean();
        mPresenterFactory = null;
        mCallback = null;
    }

    private void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

    private void clean() {
        if (mWeakContext != null) {
            mWeakContext.clear();
            mWeakContext = null;
        }
    }

}
