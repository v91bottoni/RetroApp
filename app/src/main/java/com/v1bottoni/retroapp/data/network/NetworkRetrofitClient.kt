package com.v1bottoni.retroapp.data.network

import com.v1bottoni.retroapp.BuildConfig
import com.v1bottoni.retroapp.data.network.Configuration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkRetrofitClient(config: Configuration) {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client by lazy {
        val builder = OkHttpClient.Builder().retryOnConnectionFailure(true)
        config.interceptors().forEach{builder.addInterceptor(it)}
        if(BuildConfig.DEBUG){
            builder.addInterceptor(loggingInterceptor)
        }

        builder.build()
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(config.baseUrl())
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(config.moshi))
        .build()
}