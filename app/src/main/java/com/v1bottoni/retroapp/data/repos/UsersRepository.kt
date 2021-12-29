package com.v1bottoni.retroapp.data.repos

import com.v1bottoni.retroapp.data.dto.ResponseUsersDTO
import com.v1bottoni.retroapp.data.dto.UserErrorResponse
import com.v1bottoni.retroapp.data.network.NetworkResource
import com.v1bottoni.retroapp.data.network.api.UsersAPI
import com.v1bottoni.retroapp.data.network.call.usersApiCall
import com.v1bottoni.retroapp.data.network.safeApiCall

class UsersRepository(private val usersAPI: UsersAPI) {

    suspend fun getUsersList(): NetworkResource<ResponseUsersDTO, UserErrorResponse>{
        return usersApiCall {
            usersAPI.getUsersList()
        }
    }
}