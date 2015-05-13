package com.{company_name}.android.{app_package_name_prefix}.data;

import com.{company_name}.{app_package_name_prefix}.model.MyModel;

import rx.Observable;

public interface DataSource {

    Observable<MyModel> getMyModel(int number);

    Observable<MyModel> getLatestMyModel();
}
