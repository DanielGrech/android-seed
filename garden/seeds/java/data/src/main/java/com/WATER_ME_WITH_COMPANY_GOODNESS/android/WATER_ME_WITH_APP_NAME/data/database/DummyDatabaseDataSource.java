package com.{company_name}.android.{app_package_name_prefix}.data.database;

import com.{company_name}.{app_package_name_prefix}.model.MyModel;

import rx.Observable;

public class DummyDatabaseDataSource implements DatabaseDataSource {

	public void save(MyModel model) {
		// No-op
	}

	public Observable<MyModel> getMyModel(int number) {
		return Observable.empty();
	}

    public Observable<MyModel> getLatestMyModel() {
    	return Observable.empty();
    }
}