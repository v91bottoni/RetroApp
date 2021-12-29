package com.v1bottoni.retroapp.data.network.api

import com.v1bottoni.retroapp.data.dto.ResponseUsersDTO
import retrofit2.Response
import retrofit2.http.GET

interface UsersAPI {
    @GET("user?limit=10")
    suspend fun getUsersList(): Response<ResponseUsersDTO>
}