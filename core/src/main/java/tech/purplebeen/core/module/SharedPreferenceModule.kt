package tech.purplebeen.core.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import tech.purplebeen.core.annotation.qualifier.ForApplication
import tech.purplebeen.core.annotation.qualifier.ForPreference
import javax.inject.Singleton

@Module
class SharedPreferenceModule {
    companion object {
        private val PREF_NAME = "git_issue"
    }
    @Provides
    @Singleton
    @ForPreference
    fun provideSharedPreferences(@ForApplication context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }
}