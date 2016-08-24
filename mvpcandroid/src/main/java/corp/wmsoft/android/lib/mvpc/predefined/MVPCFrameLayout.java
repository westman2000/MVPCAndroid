package corp.wmsoft.android.lib.mvpc.predefined;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import corp.wmsoft.android.lib.mvpc.delegate.IMVPCDelegate;
import corp.wmsoft.android.lib.mvpc.delegate.MVPCDelegate;
import corp.wmsoft.android.lib.mvpc.exceptions.MVPCViewNotImplementedException;
import corp.wmsoft.android.lib.mvpc.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpc.view.IMVPCView;


/**
 * Created by admin on 8/6/16.
 *
 */
public abstract class MVPCFrameLayout<V extends IMVPCView, P extends IMVPCPresenter<V>> extends FrameLayout implements IMVPCDelegate.ICallback<V, P> {

    /**/
    private MVPCDelegate<V, P> mMvpcDelegate;


    public MVPCFrameLayout(Context context) {
        super(context);
        init(context);
    }

    public MVPCFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MVPCFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MVPCFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    /**/
    protected abstract IMVPCPresenterFactory<V, P> providePresenterFactory();

    /**/
    protected abstract int provideUniqueIdentifier();

    @Override
    public void onInitializePresenter(P presenter) {
        // hook for subclasses
    }

    @Override
    public void onDestroyPresenter() {
        // hook for subclasses
    }

    @CallSuper
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //noinspection unchecked
        getMvpсDelegate().onAttachView((V) this, this);
    }

    @CallSuper
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getMvpсDelegate().onDetachView();
    }

    protected P getPresenter() {
        return getMvpсDelegate().getPresenter();
    }

    /**
     * @return The {@link MVPCDelegate} being used by this Fragment.
     */
    private MVPCDelegate<V, P> getMvpсDelegate() {
        if (mMvpcDelegate == null) {
            mMvpcDelegate = new MVPCDelegate<>();
        }
        return mMvpcDelegate;
    }

    private void init(Context context) {

        if (!(this instanceof IMVPCView))
            throw new MVPCViewNotImplementedException();

        getMvpсDelegate().onCreate(context, ((AppCompatActivity)context).getSupportLoaderManager(), providePresenterFactory(), provideUniqueIdentifier(), this);
    }

}