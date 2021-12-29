package com.v1bottoni.retroapp.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseUsersDTO(
    @Json(name = "data")
    val results: List<UserDTO>
) {
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
