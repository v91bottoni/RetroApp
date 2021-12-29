package com.v1bottoni.retroapp.di

import com.v1bottoni.retroapp.viewmodels.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UsersViewModel(get(), get()) }
}