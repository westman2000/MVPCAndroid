package corp.wmsoft.android.lib.mvpcandroid.presenter;

import java.lang.ref.WeakReference;

import corp.wmsoft.android.lib.mvpcandroid.exceptions.MVPCViewNotAttachedException;
import corp.wmsoft.android.lib.mvpcandroid.interactor.MVPCUseCaseHandler;
import corp.wmsoft.android.lib.mvpcandroid.view.IMVPCView;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getView().
 */
public abstract class MVPCPresenter<V extends IMVPCView> implements IMVPCPresenter<V> {

    /**/
    private WeakReference<V> mMvpViewRef;
    /**/
    private final MVPCUseCaseHandler mUseCaseHandler;


    public MVPCPresenter() {
        this(MVPCUseCaseHandler.getInstance());
    }

    public MVPCPresenter(MVPCUseCaseHandler useCaseHandler) {
        this.mUseCaseHandler = useCaseHandler;
    }

    @Override
    public void attachView(V mvpView) {
        mMvpViewRef = new WeakReference<>(mvpView);
    }

    @Override
    public void detachView() {
        clean();
    }

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
        return mUseCaseHandler;
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    private void checkViewAttached() {
        if (!isViewAttached()) throw new MVPCViewNotAttachedException();
    }

    private boolean isViewAttached() {
        return mMvpViewRef != null && mMvpViewRef.get() != null;
    }

    private void clean() {
        if (mMvpViewRef != null) {
            mMvpViewRef.clear();
            mMvpViewRef = null;
        }
    }

}