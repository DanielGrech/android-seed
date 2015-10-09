package com.{{company_name}}.{{app_package_name_prefix}}.network;

import com.squareup.okhttp.OkHttpClient;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.GsonConverterFactory;
import rx.Observable;
import java.util.concurrent.TimeUnit;

public class NetworkDataSource implements DataSource {

    private static final long CONNECTION_TIMEOUT = TimeUnit.SECONDS.toMillis(10);

    final ApiService service;

    public NetworkDataSource(String endpoint) {
        OkHttpClient client = createDefaultHttpClient();
   
        service = new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiService.class);
    }

    private OkHttpClient createDefaultHttpClient() {
        final OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        client.setWriteTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        client.setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        return client;
    }

}
