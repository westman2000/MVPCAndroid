package corp.wmsoft.android.lib.mvpc.delegate;

import android.content.Context;
import android.support.v4.app.LoaderManager;

import corp.wmsoft.android.lib.mvpc.presenter.IMVPCPresenter;
import corp.wmsoft.android.lib.mvpc.presenter.factory.IMVPCPresenterFactory;
import corp.wmsoft.android.lib.mvpc.view.IMVPCView;


/**
 * @param <V> view type
 * @param <P> presenter type
 */
public interface IMVPCDelegate<V extends IMVPCView, P extends IMVPCPresenter<V>> {

    /**
     * @param <V> view type
     * @param <P> presenter type
     */
    interface ICallback<V extends IMVPCView, P extends IMVPCPresenter<V>> {

        void onInitializePresenter(P presenter);

        void onDestroyPresenter();

    }


    void onCreate(Context context, LoaderManager supportLoaderManager, IMVPCPresenterFactory<V, P> presenterFactory, int uniqueIdentifier, ICallback<V, P> callback);

    void onAttachView(V view, ICallback<V, P> callback);

    void onDetachView();

    P getPresenter();

}