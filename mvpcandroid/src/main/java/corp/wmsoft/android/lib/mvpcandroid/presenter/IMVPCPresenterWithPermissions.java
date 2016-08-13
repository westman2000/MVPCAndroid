package corp.wmsoft.android.lib.mvpcandroid.presenter;

import corp.wmsoft.android.lib.mvpcandroid.view.IMVPCView;

/**
 * Every presenter in the app must either implement this interface or extend MVPCPresenter
 * indicating the MvpView type that wants to be attached with.
 * @param <V> view type
 */
public interface IMVPCPresenterWithPermissions<V extends IMVPCView> extends IMVPCPresenter<V> {

    void onRequestNecessaryPermissions();

    void onNecessaryPermissionsGranted();

    void onNecessaryPermissionsNotGranted();

    void onShowRequestPermissionRationale();

    void onActuallyRequestPermission();
}