package tech.purplebeen.core.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import tech.purplebeen.core.annotation.qualifier.ForApplication
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    @Singleton
    @ForApplication
    fun provideContext(application: Application): Context {
        return application
    }
}