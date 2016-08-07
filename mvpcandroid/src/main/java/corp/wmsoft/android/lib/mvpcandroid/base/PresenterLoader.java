package corp.wmsoft.android.lib.mvpcandroid.base;

import android.content.Context;
import android.content.Loader;


/**
 * Created by admin on 8/5/16.
 *
 */
public class PresenterLoader<V extends IBaseView, P extends IBasePresenter<V>> extends Loader<P> {

    private IPresenterFactory<V,P> mPresenterFactory;
    private P                      mPresenter;


    public PresenterLoader(Context context, IPresenterFactory<V, P> presenterFactory) {
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
