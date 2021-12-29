package com.v1bottoni.retroapp.data.network.call

import com.v1bottoni.retroapp.data.dto.GenericErrorResponse
import com.v1bottoni.retroapp.data.network.safeApiCall
import retrofit2.Response

suspend inline fun <T> usersApiCall(block: () -> Response<T>) = safeApiCall<T, GenericErrorResponse>(block)
