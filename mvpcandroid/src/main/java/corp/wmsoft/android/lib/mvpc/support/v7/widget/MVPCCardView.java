package corp.wmsoft.android.lib.mvpc.support.v7.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import corp.wmsoft.android.lib.mvpc.exceptions.MVPCViewNotImplementedException;
import corp.wmsoft.android.lib.mvpc.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpc.support.v4.content.MVPCPresenterLoader;
import corp.wmsoft.android.lib.mvpc.view.IMVPCView;

/**
 * Created by admin on 8/6/16.
 *
 */
public abstract class MVPCCardView<V extends IMVPCView, P extends IMVPCPresenter<V>> extends CardView {

    /**/
    private P mPresenter;


    public MVPCCardView(Context context) {
        super(context);
        init(context);
    }

    public MVPCCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MVPCCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
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
            //noinspection unchecked
            mPresenter.attachView((V) this);
    }

    @CallSuper
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
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

    private void init(Context context) {

        if (!(this instanceof IMVPCView))
            throw new MVPCViewNotImplementedException();

        ((AppCompatActivity)context).getSupportLoaderManager().initLoader(provideUniqueIdentifier(), null, new LoaderManager.LoaderCallbacks<P>() {

            @Override
            public Loader<P> onCreateLoader(int i, Bundle bundle) {
                return new MVPCPresenterLoader<>(getContext(), providePresenterFactory());
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

    private void setPresenter(P presenter) {
        this.mPresenter = presenter;
    }

}