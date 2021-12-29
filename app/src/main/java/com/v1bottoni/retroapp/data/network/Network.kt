package com.v1bottoni.retroapp.data.network

import kotlin.reflect.KClass

class Network (private val config: Configuration){
    fun <T: Any> createServiceAPI(apiClass: KClass<T>): T{
        return NetworkRetrofitClient(config).retrofit.create(apiClass.java)
    }
}