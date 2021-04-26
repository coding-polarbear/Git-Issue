package tech.purplebeen.gitissue.feature.main

import android.app.Activity
import dagger.BindsInstance
import dagger.Subcomponent
import tech.purplebeen.core.annotation.scope.ActivityScope
import tech.purplebeen.core.module.ActivityModule

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface MainComponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder

        fun build(): MainComponent
    }

    fun inject(activity: MainActivity)
}