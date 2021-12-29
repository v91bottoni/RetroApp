package com.v1bottoni.retroapp.data.network

import com.squareup.moshi.Moshi
import okhttp3.Interceptor

interface Configuration {
    val moshi: Moshi
    fun baseUrl(): String
    fun interceptors(): List<Interceptor> = listOf()

}