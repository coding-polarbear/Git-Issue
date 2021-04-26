package tech.purplebeen.core.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.purplebeen.core.annotation.qualifier.ForAPI
import tech.purplebeen.core.annotation.qualifier.ForLogging
import javax.inject.Singleton

@Module
class APIModule {
    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
    @Provides
    @Singleton
    @ForAPI
    fun provideAPIRetrofit(@ForLogging okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
}