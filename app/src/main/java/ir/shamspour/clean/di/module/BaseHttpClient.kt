package ir.shamspour.clean.di.module

import android.app.Application
import android.content.SharedPreferences
import ir.shamspour.data.BuildConfig.DEBUG
import ir.shamspour.domain.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val TIMEOUT = 30L

@Singleton
class BaseHttpClient @Inject constructor(private val context: Application,private val sharedPreferences: SharedPreferences) {

    val okHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level =
                    if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
        )
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(headersInterceptor())
        .build()

    private fun headersInterceptor() = Interceptor { chain ->
        val token =sharedPreferences.getString(Constants.TOKEN,"")
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("authorization", "Bearer $token")
                .build()
        )
    }

}