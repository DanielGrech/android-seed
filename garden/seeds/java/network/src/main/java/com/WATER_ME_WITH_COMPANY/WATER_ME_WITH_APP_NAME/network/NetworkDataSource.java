package com.{{company_name}}.{{app_package_name_prefix}}.data.network;

import com.{{company_name}}.{{app_package_name_prefix}}.data.DataSource;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import rx.Observable;

public class NetworkDataSource implements DataSource {

    final ApiService service;

    public NetworkDataSource() {
        OkHttpClient client = new OkHttpClient();
        
        final RestAdapter restAdapter = new RestAdapter.Builder()
                // .setEndpoint(BuildConfig.API_ENDPOINT) // TODO: !!!
                .setClient(new OkClient(client))
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .build();

        service = restAdapter.create(ApiService.class);
    }

}
