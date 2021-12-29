package com.v1bottoni.retroapp.di

import com.v1bottoni.retroapp.domain.usecases.UsersUseCase
import com.v1bottoni.retroapp.domain.usecases.impl.UsersUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {

    single<UsersUseCase> { UsersUseCaseImpl(get(), get())}


}