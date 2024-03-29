package ir.shamspour.clean.di.module

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import ir.shamspour.data.exceptions.NetworkExceptionHandler
import ir.shamspour.presentation.util.DispatchersProvider
import ir.shamspour.presentation.util.DispatchersProviderImpl
import ir.shamspour.domain.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun resources(application: Application): Resources = application.resources


    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()


    @Provides
    @Singleton
    fun okHttpClient(baseHttpClient: BaseHttpClient): OkHttpClient = baseHttpClient.okHttpClient


    @Provides
    @Singleton
    fun retrofit( baseRetrofit: BaseRetrofit): Retrofit = baseRetrofit.retrofit


    @Provides
    @Singleton
    fun dispatcher(dispatchersProvider: DispatchersProviderImpl): DispatchersProvider = dispatchersProvider.dispatchersProvider

    @Provides
    @Singleton
    fun apiExceptionHandler(gson: Gson): NetworkExceptionHandler = NetworkExceptionHandler(gson)

    @Provides
    @Singleton
    fun provideGlide(context:Application): RequestManager {
        return Glide.with(context)
    }


    @Provides
    @Singleton
    fun provideSharedPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences(Constants.TAG,MODE_PRIVATE)
    }



}