package corp.wmsoft.android.lib.mvpc.predefined;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.app.DialogFragment;

import corp.wmsoft.android.lib.mvpc.delegate.IMVPCDelegate;
import corp.wmsoft.android.lib.mvpc.delegate.MVPCDelegate;
import corp.wmsoft.android.lib.mvpc.exceptions.MVPCViewNotImplementedException;
import corp.wmsoft.android.lib.mvpc.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpc.view.IMVPCView;


/**
 * Created by westman on 8/5/16.
 *
 */
public abstract class MVPCSupportDialogFragment<V extends IMVPCView, P extends IMVPCPresenter<V>> extends DialogFragment implements IMVPCDelegate.ICallback<V, P> {

    /**/
    private static final int UNIQUE_IDENTIFIER = 58979;

    /**/
    private MVPCDelegate<V, P> mMvpcDelegate;

    /**/
    protected abstract IMVPCPresenterFactory<V, P> providePresenterFactory();

    public MVPCSupportDialogFragment() {
        if (!(this instanceof IMVPCView))
            throw new MVPCViewNotImplementedException();
    }

    @CallSuper
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpсDelegate().onCreate(getActivity(), getLoaderManager(), providePresenterFactory(), UNIQUE_IDENTIFIER, this);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        //noinspection unchecked
        getMvpсDelegate().onAttachView((V) this, this);
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
        //noinspection unchecked
        getMvpсDelegate().onAttachView((V) this, this);
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpсDelegate().onDetachView();
    }

    @Override
    public void onInitializePresenter(P presenter) {
        // hook for subclasses
    }

    @Override
    public void onDestroyPresenter() {
        // hook for subclasses
    }

    protected P getPresenter() {
        return getMvpсDelegate().getPresenter();
    }

    /**
     * @return The {@link MVPCDelegate} being used by this Fragment.
     */
    private MVPCDelegate<V,P> getMvpсDelegate() {
        if (mMvpcDelegate == null) {
            mMvpcDelegate = new MVPCDelegate<>();
        }
        return mMvpcDelegate;
    }
}