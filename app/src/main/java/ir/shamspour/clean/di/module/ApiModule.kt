package ir.shamspour.clean.di.module

import ir.shamspour.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Provides
    fun detalInventoryApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)


}