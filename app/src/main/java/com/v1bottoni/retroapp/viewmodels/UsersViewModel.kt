package com.v1bottoni.retroapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.v1bottoni.retroapp.data.dto.GenericErrorResponse
import com.v1bottoni.retroapp.domain.models.Response
import com.v1bottoni.retroapp.domain.models.User
import com.v1bottoni.retroapp.domain.usecases.UsersUseCase
import com.v1bottoni.retroapp.extensions.unknownError
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UsersViewModel(
    app: Application,
    private val usersUseCase: UsersUseCase
) : AndroidViewModel(app) {

    companion object {
        private val TAG = UsersViewModel::class.java.toString()
    }

    private val _users = MutableLiveData<Response<List<User>, GenericErrorResponse>>()
    val users: LiveData<Response<List<User>, GenericErrorResponse>> = _users

    fun fetchAllUsers() {
        viewModelScope.launch {
            usersUseCase.getUsersList()
                .onStart {
                    emit(Response.Loading())
                }.catch { cause: Throwable ->
                    Log.d(TAG, cause?.localizedMessage ?: String.unknownError(TAG,
                        {
                            cause.stackTrace.first().lineNumber
                        })
                    )
                    emit(
                        Response.Error(
                        errorMessage = cause.message.orEmpty(),
                        exception = cause
                    ))

                }.collect {
                    if (it is Response.Error) {
                        Log.d(TAG,it.errorMessage)
                    }
                    _users.postValue(it)
                }
        }
    }
}