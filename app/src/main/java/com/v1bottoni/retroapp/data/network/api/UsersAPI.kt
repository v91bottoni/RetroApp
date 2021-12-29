package com.v1bottoni.retroapp.data.network.api

import com.v1bottoni.retroapp.data.dto.ResponseUsersDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersAPI {
    @GET("user")
    suspend fun getUsersList(@Query("limit") limit: String = "10"): Response<ResponseUsersDTO>
}