package corp.wmsoft.android.lib.mvpcandroid.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import corp.wmsoft.android.lib.mvpcandroid.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcandroid.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpcandroid.presenter.loader.MVPCPresenterLoader;
import corp.wmsoft.android.lib.mvpcandroid.view.IMVPCView;

/**
 * Created by admin on 8/6/16.
 *
 */
public abstract class MVPCFrameLayout<V extends IMVPCView, P extends IMVPCPresenter<V>> extends FrameLayout implements LoaderManager.LoaderCallbacks<P> {

    /**/
    private P mPresenter;


    public MVPCFrameLayout(Context context) {
        super(context);
        ((Activity)context).getLoaderManager().initLoader(provideUniqueIdentifier(), null, this);
    }

    public MVPCFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ((Activity)context).getLoaderManager().initLoader(provideUniqueIdentifier(), null, this);
    }

    public MVPCFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ((Activity)context).getLoaderManager().initLoader(provideUniqueIdentifier(), null, this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MVPCFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        ((Activity)context).getLoaderManager().initLoader(provideUniqueIdentifier(), null, this);
    }

    /**/
    protected abstract IMVPCPresenterFactory<V, P> providePresenterFactory();

    /**/
    protected abstract void onInitializePresenter(P presenter);

    /**/
    protected abstract int provideUniqueIdentifier();

    @CallSuper
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mPresenter != null)
            mPresenter.attachView((V) this);
    }

    @CallSuper
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    @Override
    public Loader<P> onCreateLoader(int i, Bundle bundle) {
        return new MVPCPresenterLoader<>(getContext(), providePresenterFactory());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P presenter) {
        this.mPresenter = presenter;
        onInitializePresenter(presenter);
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        // because loader reset called before onDetachedFromWindow, so clean here for no leaks
        getPresenter().detachView();
        this.mPresenter = null;
    }

    protected P getPresenter() {
        return mPresenter;
    }

}