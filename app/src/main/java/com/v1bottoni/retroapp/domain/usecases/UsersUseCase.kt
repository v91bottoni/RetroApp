package com.v1bottoni.retroapp.domain.usecases

import com.v1bottoni.retroapp.data.dto.GenericErrorResponse
import com.v1bottoni.retroapp.domain.models.Response
import com.v1bottoni.retroapp.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UsersUseCase {
fun getUsersList(): Flow<Response<List<User>, GenericErrorResponse>>
}
