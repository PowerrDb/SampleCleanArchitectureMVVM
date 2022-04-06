package com.bestpractises.razisample.di.qualifier

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlSSO

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlMedia

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlMessaging