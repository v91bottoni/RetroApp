package com.v1bottoni.retroapp.extensions

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val moshi = Moshi.Builder().apply { add(KotlinJsonAdapterFactory()) }.build()

inline fun <reified T: Any> Moshi.fromJson(json:String):T? {
    return adapter(T::class.java).apply {
        lenient()
    }.fromJson(json)
}

inline fun <reified T : Any> Moshi.toJson(
    obj: T
): String {
    val jsonAdapter = adapter(T::class.java)
    return jsonAdapter.toJson(obj)
}