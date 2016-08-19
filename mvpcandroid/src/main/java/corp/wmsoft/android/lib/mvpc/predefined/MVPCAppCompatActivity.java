package corp.wmsoft.android.lib.mvpc.predefined;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import corp.wmsoft.android.lib.mvpc.delegate.IMVPCDelegate;
import corp.wmsoft.android.lib.mvpc.delegate.MVPCDelegate;
import corp.wmsoft.android.lib.mvpc.exceptions.MVPCViewNotImplementedException;
import corp.wmsoft.android.lib.mvpc.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpc.view.IMVPCView;


/**
 * Created by admin on 8/5/16.
 *
 */
public abstract class MVPCAppCompatActivity<V extends IMVPCView, P extends IMVPCPresenter<V>> extends AppCompatActivity implements IMVPCDelegate.ICallback<V, P> {

    /**/
    private static final int UNIQUE_IDENTIFIER = 31415;

    /**/
    private MVPCDelegate<V, P> mMvpcDelegate;

    /**/
    protected abstract IMVPCPresenterFactory<V, P> providePresenterFactory();


    public MVPCAppCompatActivity() {
        if (!(this instanceof IMVPCView))
            throw new MVPCViewNotImplementedException();
    }

    /**/
    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpсDelegate().onCreate(this, getSupportLoaderManager(), providePresenterFactory(), UNIQUE_IDENTIFIER, this);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        //noinspection unchecked
        getMvpсDelegate().onAttachView((V) this, this);
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
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
     * @return The {@link MVPCDelegate} being used by this Activity.
     */
    private MVPCDelegate<V,P> getMvpсDelegate() {
        if (mMvpcDelegate == null) {
            mMvpcDelegate = new MVPCDelegate<>();
        }
        return mMvpcDelegate;
    }

}
