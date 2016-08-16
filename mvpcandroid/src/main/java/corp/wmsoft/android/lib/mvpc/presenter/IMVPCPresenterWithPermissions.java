package corp.wmsoft.android.lib.mvpc.presenter;

import corp.wmsoft.android.lib.mvpc.view.IMVPCView;

/**
 * Every presenter in the app must either implement this interface or extend MVPCPresenter
 * indicating the MvpView type that wants to be attached with.
 * @param <V> view type
 */
public interface IMVPCPresenterWithPermissions<V extends IMVPCView> extends IMVPCPresenter<V> {

    void onRequestNecessaryPermissions();

    // will be called before onResume()
    void onNecessaryPermissionsGranted();

    // will be called before onResume
    void onNecessaryPermissionsNotGranted();

    void onShowRequestPermissionRationale();

    void onActuallyRequestPermission();
}