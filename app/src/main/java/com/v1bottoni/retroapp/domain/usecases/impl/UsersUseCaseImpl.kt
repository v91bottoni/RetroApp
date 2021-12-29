package com.v1bottoni.retroapp.domain.usecases.impl

import com.v1bottoni.retroapp.data.dto.GenericErrorResponse
import com.v1bottoni.retroapp.data.network.NetworkResource
import com.v1bottoni.retroapp.data.repos.UsersRepository
import com.v1bottoni.retroapp.domain.mappers.UsersMapper
import com.v1bottoni.retroapp.domain.models.Response
import com.v1bottoni.retroapp.domain.models.User
import com.v1bottoni.retroapp.domain.usecases.UsersUseCase
import com.v1bottoni.retroapp.extensions.unknownError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


class UsersUseCaseImpl(
    private val usersMapper: UsersMapper,
    private val usersRepository: UsersRepository
) : UsersUseCase {

    companion object {
        private val TAG = UsersUseCaseImpl::class.java.toString()
    }

    override fun getUsersList(): Flow<Response<List<User>, GenericErrorResponse>> {
        return flow {
            emit(
                withContext(Dispatchers.IO) {
                    val response = async {
                        usersRepository.getUsersList()
                    }
                    response.await()
                }
            )
        }.map {
            when (it) {
                is NetworkResource.Success -> {
                    val usersList = usersMapper.mapFrom(it.data?.data ?: emptyList())
                    Response.Success(usersList)
                }
                is NetworkResource.Error -> {
                    Response.Error(
                        errorMessage = it.response?.message()
                            ?: String.unknownError(TAG,{Throwable().stackTrace.first().lineNumber}),
                        networkError = it.error
                    )
                }
            }
        }.flowOn(Dispatchers.Default)
    }


}
