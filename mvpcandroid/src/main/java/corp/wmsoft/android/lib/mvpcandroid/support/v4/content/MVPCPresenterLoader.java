package corp.wmsoft.android.lib.mvpcandroid.support.v4.content;

import android.content.Context;
import android.support.v4.content.Loader;

import corp.wmsoft.android.lib.mvpcandroid.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpcandroid.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpcandroid.view.IMVPCView;


/**
 * Created by admin on 8/5/16.
 *
 */
public class MVPCPresenterLoader<V extends IMVPCView, P extends IMVPCPresenter<V>> extends Loader<P> {

    private IMVPCPresenterFactory<V,P> mPresenterFactory;
    private P                      mPresenter;


    public MVPCPresenterLoader(Context context, IMVPCPresenterFactory<V, P> presenterFactory) {
        super(context);
        mPresenterFactory = presenterFactory;
    }

    @Override
    public void deliverResult(P data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        // if we already own a presenter instance, simply deliver it.
        if (mPresenter != null) {
            deliverResult(mPresenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        // Create the Presenter using the Factory
        mPresenter = mPresenterFactory.create();

        // Deliver the result
        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        if (mPresenter != null) {
            mPresenter.onDestroyed();
            mPresenter = null;
        }
        if (mPresenterFactory != null) {
            mPresenterFactory = null;
        }
    }
}
