package corp.wmsoft.android.lib.mvpc.presenter;

import android.support.annotation.CallSuper;

import java.lang.ref.WeakReference;

import corp.wmsoft.android.lib.mvpc.exceptions.MVPCUseCaseHandlerNotSetException;
import corp.wmsoft.android.lib.mvpc.exceptions.MVPCViewNotAttachedException;
import corp.wmsoft.android.lib.mvpc.interactor.MVPCUseCaseHandler;
import corp.wmsoft.android.lib.mvpc.view.IMVPCView;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView().<br/>
 * It also handles keeping a reference to the mvpView that can be accessed from the children classes by calling getView().
 * It also handles keeping a reference to the useCaseHandler that can be accessed from the children classes by calling getUseCaseHandler().
 */
public abstract class MVPCPresenter<V extends IMVPCView> implements IMVPCPresenter<V> {

    /**/
    private WeakReference<V> mMvpViewRef;
    /**/
    private MVPCUseCaseHandler mUseCaseHandler;


    public MVPCPresenter() {
    }

    public MVPCPresenter(MVPCUseCaseHandler useCaseHandler) {
        this.mUseCaseHandler = useCaseHandler;
    }

    @CallSuper
    @Override
    public void attachView(V mvpView) {
        mMvpViewRef = new WeakReference<>(mvpView);
    }

    @CallSuper
    @Override
    public boolean isViewAttached() {
        return mMvpViewRef != null && mMvpViewRef.get() != null;
    }

    @CallSuper
    @Override
    public void detachView() {
        clean();
    }

    @CallSuper
    @Override
    public void onDestroyed() {
        clean();
    }

    /**
     * Get the attached view.
     * You will receive MvpViewNotAttachedException if the view is not attached
     *
     * @return concrete view instance
     */
    protected V getView() {
        checkViewAttached();
        return mMvpViewRef.get();
    }

    protected MVPCUseCaseHandler getUseCaseHandler() {
        if (mUseCaseHandler == null) throw new MVPCUseCaseHandlerNotSetException();
        return mUseCaseHandler;
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    private void checkViewAttached() {
        if (!isViewAttached()) throw new MVPCViewNotAttachedException();
    }

    private void clean() {
        if (mMvpViewRef != null) {
            mMvpViewRef.clear();
            mMvpViewRef = null;
        }
    }

}