package com.arturomarmolejo.amiiboappamcompose.di

import com.arturomarmolejo.amiiboappamcompose.rest.AmiiboServiceApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
* Network that provides network dependencies
* */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    /**
     * Provides a [Gson] instance
     */
    @Provides
    fun providesGson(): Gson = Gson()

    /**
     * Provides a [Retrofit] instance with [BASE_URL] and [GsonConverterFactory]
     * @param okHttpClient provides the [OkHttpClient] used to build [Retrofit]
     * @param gson provides the [Gson] instance to build the [Retrofit]
     */
    @Provides
    fun providesAmiiboServiceApi(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): AmiiboServiceApi =
        Retrofit.Builder()
            .baseUrl(AmiiboServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(AmiiboServiceApi::class.java)


    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    /**
     * Provides an instance of [HttpLoggingInterceptor] with a BODY Level logging enabled
     */

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Provides a [CoroutineDispatcher] instance with IO context
     */
    @Provides
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO



}