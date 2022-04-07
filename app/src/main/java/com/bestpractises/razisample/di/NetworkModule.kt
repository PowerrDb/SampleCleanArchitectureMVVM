package com.bestpractises.razisample.di


import android.content.Context
import android.content.Intent
import com.bestpractises.razisample.AppSession
import com.bestpractises.razisample.BuildConfig
import com.bestpractises.razisample.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideBaseUrlAPI(): String = BuildConfig.API_URL

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
    }

    @Provides
    fun providesOkHttpApiClient(
        @ApplicationContext appContext: Context,
        loggingInterceptor: HttpLoggingInterceptor,
        okHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient {
        return if (BuildConfig.DEBUG)
            okHttpClientBuilder
                .addInterceptor(loggingInterceptor)
                .build()
        else okHttpClientBuilder
            .build()

    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesRetrofitAPI(
       baseUrl: String,
        converter: Converter.Factory,
         okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }


    @Singleton
    @Provides
    fun providesApiServiceAPI(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}