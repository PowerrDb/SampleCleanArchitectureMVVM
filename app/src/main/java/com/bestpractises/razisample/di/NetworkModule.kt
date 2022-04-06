package com.bestpractises.razisample.di


import android.content.Context
import android.content.Intent
import com.bestpractises.razisample.AppSession
import com.bestpractises.razisample.BuildConfig
import com.bestpractises.razisample.di.qualifier.*
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

    @BaseUrlAPI
    @Provides
    fun provideBaseUrlAPI(): String = BuildConfig.API_URL

    @BaseUrlSSO
    @Provides
    fun provideBaseUrlBaseUrlSSO(): String = BuildConfig.SSO_URL

    @BaseUrlMedia
    @Provides
    fun provideBaseUrlMedia(): String = BuildConfig.MEDIA_URL

    @BaseUrlMessaging
    @Provides
    fun provideBaseUrlMessaging(): String = BuildConfig.MESSAGING_URL


    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
    }

    @OkHttpApiWithoutToken
    @Provides
    fun providesOkHttpClient(
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

    @OkHttpApi
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


    @RetrofitAPIWithoutToken
    @Provides
    fun providesRetrofitAPIWithoutToken(
        @BaseUrlAPI baseUrl: String,
        converter: Converter.Factory,
        @OkHttpApiWithoutToken okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }


    @RetrofitAPI
    @Provides
    fun providesRetrofitAPI(
        @BaseUrlAPI baseUrl: String,
        converter: Converter.Factory,
        @OkHttpApi okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @RetrofitSSO
    @Provides
    fun providesRetrofitSSO(
        @BaseUrlSSO baseUrl: String,
        converter: Converter.Factory,
        @OkHttpApi okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @RetrofitMedia
    @Provides
    fun providesRetrofitMedia(
        @BaseUrlMedia baseUrl: String,
        converter: Converter.Factory,
        @OkHttpApi okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @RetrofitMessaging
    @Provides
    fun providesRetrofitMessaging(
        @BaseUrlMessaging baseUrl: String,
        converter: Converter.Factory,
        @OkHttpApi okHttpClient: OkHttpClient
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
    fun providesApiServiceAPIWithoutToken(@RetrofitAPIWithoutToken retrofit: Retrofit): ApiServiceWithoutToken {
        return retrofit.create(ApiServiceWithoutToken::class.java)
    }

    @Singleton
    @Provides
    fun providesApiServiceAPI(@RetrofitAPI retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesApiServiceSSO(@RetrofitSSO retrofit: Retrofit): ApiServiceSSO {
        return retrofit.create(ApiServiceSSO::class.java)
    }

    @Singleton
    @Provides
    fun providesApiServiceMEDIA(@RetrofitMedia retrofit: Retrofit): ApiServiceMedia {
        return retrofit.create(ApiServiceMedia::class.java)
    }

    @Singleton
    @Provides
    fun providesApiServiceMessaging(@RetrofitMessaging retrofit: Retrofit): ApiServiceMessaging {
        return retrofit.create(ApiServiceMessaging::class.java)
    }
}