package com.v1bottoni.retroapp.data.network

import android.util.Log
import com.squareup.moshi.JsonDataException
import com.v1bottoni.retroapp.data.dto.ErrorResponse
import com.v1bottoni.retroapp.extensions.fromJson
import com.v1bottoni.retroapp.extensions.moshi
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

const val HTTP_ERROR_CODE= "HTTP code"
const val TAG = "SAFE_CALL_API"

suspend inline fun <T,reified E: ErrorResponse> safeApiCall(call: () -> Response<T>): NetworkResource<T, E> {
    val result = runCatching(call)
    return if (result.isSuccess){
        val response = result.getOrNull()!!
        if (response.isSuccessful){
            val responseBody = response.body()
            NetworkResource.Success<T,E>(response, responseBody)
        }else{
            Log.d(TAG,
                "${HTTP_ERROR_CODE} ${response.code()}")

            val errorBody = response.errorBody()
            when (errorBody != null){
                true -> NetworkResource.Error<T,E>(response,
                    NetworkError.HttpError(response.code(), deserializeError(errorBody)))
                false -> NetworkResource.Error<T,E>(response,
                    NetworkError.HttpError(response.code(), null))
            }
        }
    }else{
        val exception = result.exceptionOrNull()
        Log.d(TAG, exception?.localizedMessage ?: "Exception is null!")
        return when(exception){
            is SocketTimeoutException -> NetworkResource.Error(null, NetworkError.Timeout())
            is IOException -> NetworkResource.Error(null, NetworkError.IOError())
            is JsonDataException -> NetworkResource.Error(null, NetworkError.JsonParsingError(exception))
            else -> NetworkResource.Error(null, NetworkError.Unknown())

        }
    }
}

inline fun <reified E : Any> deserializeError(responseBody: ResponseBody): E? {
    val body = responseBody.string()
    val result = runCatching {
        moshi.fromJson<E>(body)
    }
    return result.getOrNull()
}
