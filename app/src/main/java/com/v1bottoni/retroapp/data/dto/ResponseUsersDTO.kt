package com.v1bottoni.retroapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseUsersDTO(
    @Json(name = "data")
    val data: List<UserDTO>,
    @Json(name = "total")
    val total: Int,
    @Json(name = "page")
    val page: Int,
    @Json(name = "limit")
    val limit: Int) {
    @JsonClass(generateAdapter = true)
    data class UserDTO (
        @Json(name= "id")
        val id:String ,
        @Json(name = "title")
        val title : String,
        @Json(name = "firstName")
        val firstName : String,
        @Json(name = "lastName")
        val lastName : String,
        @Json(name = "picture")
        val picture : String)
}
