package corp.wmsoft.android.lib.mvpcandroid.repository;

import java.util.Collection;

import corp.wmsoft.android.lib.mvpcandroid.datamodel.IBaseDataModel;


/**
 * The root data source interface for every mvp data sources
 */
public interface IBaseDataSource {

    interface IBaseDataSourceCallback {

        void onError(Error error);

    }

    interface IDataSourceCallback extends IBaseDataSourceCallback {

        void onSuccess();

    }

    interface IRequestDataModelCollectionCallback<DM extends IBaseDataModel, DMC extends Collection<DM>> extends IBaseDataSourceCallback {

        void onDataModelCollectionAvailable(DMC notes);

        void onDataModelCollectionNotAvailable();

    }

    interface IRequestDataModelCallback<DM extends IBaseDataModel> extends IBaseDataSourceCallback {

        void onDataModelAvailable(DM note);

        void onDataModelNotAvailable();

    }
}
