package corp.wmsoft.android.lib.mvpcandroid.base;

import java.lang.ref.WeakReference;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getView().
 */
public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    /**/
    private WeakReference<V> mMvpViewRef;


    public BasePresenter() {
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

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    private void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
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

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(IBaseView) before requesting data to the Presenter");
        }
    }
}