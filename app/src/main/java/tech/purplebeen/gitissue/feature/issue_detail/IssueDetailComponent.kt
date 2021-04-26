package tech.purplebeen.gitissue.feature.issue_detail

import android.app.Activity
import dagger.BindsInstance
import dagger.Subcomponent
import tech.purplebeen.core.annotation.scope.ActivityScope
import tech.purplebeen.core.module.ActivityModule

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface IssueDetailComponent{
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: Activity): Builder

        fun build(): IssueDetailComponent
    }

    fun inject(activity: IssueDetailActivity)
}