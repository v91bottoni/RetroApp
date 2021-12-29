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
data class GenericErrorResponse(
    @Json(name = "code")
    val errorCode: String?
) : ErrorResponse() {
    var httpCode: Int? = null

    override fun getErrorMessage(): String {
        return errorCode.orEmpty()
    }
}