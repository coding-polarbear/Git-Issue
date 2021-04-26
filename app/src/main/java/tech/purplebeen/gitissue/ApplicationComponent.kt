package tech.purplebeen.gitissue

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import tech.purplebeen.core.module.APIModule
import tech.purplebeen.core.module.ApplicationModule
import tech.purplebeen.core.module.HttpClientModule
import tech.purplebeen.gitissue.feature.main.MainComponent
import tech.purplebeen.gitissue.mvvm.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        HttpClientModule::class,
        APIModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: BaseApplication)

    fun mainComponentBuilder(): MainComponent.Builder
}