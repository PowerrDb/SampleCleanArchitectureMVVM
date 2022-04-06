package com.bestpractises.razisample.di.qualifier

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpApiWithoutToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpApi


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitSSO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitMedia

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitMessaging


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitAPIWithoutToken
