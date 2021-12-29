package com.v1bottoni.retroapp.domain.models

import android.webkit.ConsoleMessage
import com.v1bottoni.retroapp.data.dto.ErrorResponse
import com.v1bottoni.retroapp.data.network.NetworkError
import java.lang.Exception

sealed class Response<out T, out E: ErrorResponse> {

    data class Loading(
        @Transient private val empty: Int = 0): Response<Nothing, Nothing>()

    data class Success<T>(
        val data: T
    ): Response<T, Nothing>()

    data class Error<E: ErrorResponse>(
        val errorMessage: String,
        val exception: Throwable? = null,
        val networkError: NetworkError<E>? = null
    ): Response<Nothing, E>()
}
