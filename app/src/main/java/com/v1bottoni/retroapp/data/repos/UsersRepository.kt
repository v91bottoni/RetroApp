package com.v1bottoni.retroapp.data.repos

import com.v1bottoni.retroapp.data.dto.ResponseUsersDTO
import com.v1bottoni.retroapp.data.dto.GenericErrorResponse
import com.v1bottoni.retroapp.data.network.NetworkResource
import com.v1bottoni.retroapp.data.network.api.UsersAPI
import com.v1bottoni.retroapp.data.network.call.usersApiCall

class UsersRepository(private val usersAPI: UsersAPI) {

    suspend fun getUsersList(): NetworkResource<ResponseUsersDTO, GenericErrorResponse>{
        return usersApiCall {
            usersAPI.getUsersList()
        }
    }
}