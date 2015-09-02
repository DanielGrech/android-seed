package com.{company_name}.android.{app_package_name_prefix}.data.network;

import com.{company_name}.{app_package_name_prefix}.model.MyModel;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface {app_class_prefix}Service {

    @GET("/{num}/info.0.json")
    Observable<MyModel> getMyModel(@Path("num") int number);

    @GET("/info.0.json")
    Observable<MyModel> getLatestMyModel();

}
