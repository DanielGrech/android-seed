package com.{{company_name}}.{{app_package_name_prefix}}

import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.OkHttpClient
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.GsonConverterFactory
import java.util.concurrent.TimeUnit

public class NetworkDataSource : DataSource {

    companion object {
        private val CONNECTION_TIMEOUT = TimeUnit.SECONDS.toMillis(10)

        fun createDefaultHttpClient(): OkHttpClient {
            val client = OkHttpClient()
            client.setReadTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            client.setWriteTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            client.setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            return client
        }
    }

    val apiService: ApiService

    private constructor(builder: NetworkDataSource.Builder) {
        val client = createDefaultHttpClient()
        client.networkInterceptors().addAll(builder.networkInterceptors)

        apiService = Retrofit.Builder()
                .baseUrl(builder.endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

    public class Builder {
        public var logging: Boolean = false

        public var endpoint: String = ""

        public var networkInterceptors: List<Interceptor> = emptyList()

        public fun build(): NetworkDataSource {
            return NetworkDataSource(this)
        }
    }
}

fun networkDataSource(init: NetworkDataSource.Builder.() -> Unit): NetworkDataSource {
    val builder = NetworkDataSource.Builder()
    builder.init()
    return builder.build()
}