package tech.purplebeen.core.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import rx.android.BuildConfig
import tech.purplebeen.core.annotation.qualifier.ForLogging
import javax.inject.Singleton

@Module
class HttpClientModule {
    @Provides
    @Singleton
    @ForLogging
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        if(BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(loggingInterceptor)
        }

        return clientBuilder.build()
    }
}