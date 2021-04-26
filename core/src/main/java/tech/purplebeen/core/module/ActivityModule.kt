package tech.purplebeen.core.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import tech.purplebeen.core.annotation.qualifier.ForActivity
import tech.purplebeen.core.annotation.scope.ActivityScope

@Module
class ActivityModule {
    @Provides
    @ActivityScope
    @ForActivity
    fun provideContext(activity: Activity): Context {
        return activity
    }
}