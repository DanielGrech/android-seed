package com.{company_name}.android.{app_package_name_prefix}.data.network;

import com.{company_name}.android.{app_package_name_prefix}.data.BuildConfig;
import com.{company_name}.android.{app_package_name_prefix}.data.DataSource;
import com.{company_name}.{app_package_name_prefix}.model.MyModel;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import rx.Observable;

public class NetworkDataSource implements DataSource {

    final {app_class_prefix}Service service;

    public NetworkDataSource() {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new StethoInterceptor());

        final RestAdapter restAdapter = new RestAdapter.Builder()
                // .setEndpoint(BuildConfig.API_ENDPOINT) // TODO: !!!
                .setClient(new OkClient(client))
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .build();

        service = restAdapter.create({app_class_prefix}Service.class);
    }


    @Override
    public Observable<MyModel> getMyModel(int number) {
        return service.getMyModel(number);
    }

    @Override
    public Observable<MyModel> getLatestMyModel() {
        return service.getLatestMyModel();
    }
}
