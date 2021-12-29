package com.v1bottoni.retroapp.data.network

import com.squareup.moshi.JsonDataException
import com.v1bottoni.retroapp.data.dto.ErrorResponse
import org.xmlpull.v1.XmlPullParserException
import retrofit2.Response

sealed class NetworkResource<T, E: ErrorResponse>(
    val data: T? = null,
    val error: NetworkError<E>? = null
){
    class Success<T, E: ErrorResponse>(
        val response: Response<T>,
        data: T? = null
    ) : NetworkResource<T, E>(data = data)

    class Error<T, E: ErrorResponse>(val response: Response<T>?, error: NetworkError<E>) :
        NetworkResource<T, E>(error = error)
}


sealed class NetworkError<E: ErrorResponse> {
    class HttpError<E: ErrorResponse>(val httpCode: Int, val data: E?) : NetworkError<E>()
    class IOError<E: ErrorResponse> : NetworkError<E>()
    class Timeout<E: ErrorResponse> : NetworkError<E>()
    class Unknown<E: ErrorResponse> : NetworkError<E>()
    class JsonParsingError<E: ErrorResponse>(val exception: JsonDataException) : NetworkError<E>()
    class XmlParsingError<E: ErrorResponse>(val exception: XmlPullParserException): NetworkError<E>()
}