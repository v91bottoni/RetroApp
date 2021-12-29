package com.v1bottoni.retroapp.di

import com.v1bottoni.retroapp.domain.mappers.UsersMapper
import org.koin.dsl.module
import kotlin.math.sin

val mapperModule= module {
    single { UsersMapper() }
}