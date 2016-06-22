package corp.wmsoft.android.lib.mvpcandroid.presenter;

import java.lang.ref.WeakReference;

import corp.wmsoft.android.lib.mvpcandroid.exceptions.MvpcViewNotAttachedException;
import corp.wmsoft.android.lib.mvpcandroid.interactor.UseCaseHandler;
import corp.wmsoft.android.lib.mvpcandroid.view.IBaseView;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getView().
 */
public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    /**/
    private WeakReference<V> mMvpViewRef;
    /**/
    private final UseCaseHandler mUseCaseHandler;


    public BasePresenter() {
        this(UseCaseHandler.getInstance());
    }

    public BasePresenter(UseCaseHandler useCaseHandler) {
        this.mUseCaseHandler = useCaseHandler;
    }

    @Override
    public void attachView(V mvpView) {
        mMvpViewRef = new WeakReference<>(mvpView);
    }

    @Override
    public void detachView() {
        if (mMvpViewRef != null) {
            mMvpViewRef.clear();
            mMvpViewRef = null;
        }
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

    protected UseCaseHandler getUseCaseHandler() {
        return mUseCaseHandler;
    }

    /**
     * Checks if a view is attached to this presenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    protected boolean isViewAttached() {
        return mMvpViewRef != null && mMvpViewRef.get() != null;
    }

    protected void checkViewAttached() {
        if (!isViewAttached()) throw new MvpcViewNotAttachedException();
    }

}