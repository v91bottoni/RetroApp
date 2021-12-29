package com.v1bottoni.retroapp.di

import com.squareup.moshi.Moshi
import com.v1bottoni.retroapp.BuildConfig
import com.v1bottoni.retroapp.data.network.Configuration
import com.v1bottoni.retroapp.data.network.Network
import com.v1bottoni.retroapp.data.network.api.UsersAPI
import com.v1bottoni.retroapp.data.repos.UsersRepository
import com.v1bottoni.retroapp.extensions.moshi
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val NETWORK_CONFIGURATION = "nw_configuration"

val repositoriesModule = module {
    single { moshi }

    //Network Config
    single<Configuration>(named(NETWORK_CONFIGURATION)){
        object :Configuration{
            override val moshi: Moshi = get()

            override fun baseUrl(): String {
                return BuildConfig.APP_BASE_URL
            }

            override fun interceptors(): List<Interceptor> {
                return listOf(
                    Interceptor { chain ->
                        val request = chain.request()
                            .newBuilder()
                            .header("app-id", "61cc3bee98374711837a74eb")
                            .build()
                        chain.proceed(request)
                    }
                )
            }
        }
    }

    //API
    single {
        Network(get(named(NETWORK_CONFIGURATION))).createServiceAPI(UsersAPI::class)
    }


    //Repositories
    single { UsersRepository(get()) }

}