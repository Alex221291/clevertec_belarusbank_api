package com.clevertec_mobilelab_2022_alex_lukanev.task6.data.di

import com.clevertec_mobilelab_2022_alex_lukanev.task6.data.network.AppService
import com.clevertec_mobilelab_2022_alex_lukanev.task6.data.network.AppRepository
import com.clevertec_mobilelab_2022_alex_lukanev.task6.data.network.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://belarusbank.by"
@Module(includes = [AppBindModule::class])
class AppModule {

    @Provides
    fun getAppService(retrofit: Retrofit): AppService {
        return retrofit.create(AppService::class.java)
    }

    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

@Module
interface AppBindModule {

    @Suppress("FunctionName")
    @Binds
    fun bindAppRepositoryImpl_to_AppRepository(
        newsRepositoryImpl: AppRepositoryImpl
    ): AppRepository
}