package tech.purplebeen.gitissue

import android.app.Application

class BaseApplication: Application() {
    companion object {
        private lateinit var APP_COMPONENT: ApplicationComponent

        fun getAppComponent(): ApplicationComponent {
            return APP_COMPONENT
        }
    }
    override fun onCreate() {
        super.onCreate()

        APP_COMPONENT = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        APP_COMPONENT.inject(this)
    }
}