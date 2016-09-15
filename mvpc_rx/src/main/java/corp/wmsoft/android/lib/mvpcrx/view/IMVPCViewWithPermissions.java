package corp.wmsoft.android.lib.mvpcrx.view;

import java.util.Collection;

/**
 * The root view interface for every mvp view
 */
public interface IMVPCViewWithPermissions extends IMVPCView {

    void checkSelfPermission(Collection<String> permissions);

    void shouldShowRequestPermissionRationale(Collection<String> permissions);

    void showRationale();

    void actuallyRequestPermission(Collection<String> permissions);

    void showNecessaryPermissionsNotGranted();
}
