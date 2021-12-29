package com.v1bottoni.retroapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class ErrorResponse {
    abstract fun getErrorMessage(): String
}

@JsonClass(generateAdapter = true)
data class Error(
    @Json(name = "title")
    val title: String?,
    @Json(name = "detail")
    val detail: String?,
    @Json(name = "status")
    val status: String?
)


@JsonClass(generateAdapter = true)
data class UserErrorResponse(
    @Json(name = "error")
    val error: Error?,
): ErrorResponse() {
    override fun getErrorMessage(): String {
        return "[${error?.status}] - ${error?.title.orEmpty()}: ${error?.detail.orEmpty()}"
    }
}