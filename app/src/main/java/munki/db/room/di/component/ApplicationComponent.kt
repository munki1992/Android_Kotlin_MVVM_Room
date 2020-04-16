package munki.db.room.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import munki.db.room.GlobalApplication
import munki.db.room.di.builder.ActivityBuilder
import munki.db.room.di.module.AppModule
import javax.inject.Singleton

/**
 * Dagger를 사용하기 위한 Application 최상단 init를 위한 Component - Module을 불러오고 어디로 주입할지를 결정하는 역할
 * AndroidInjectionModule.class와 AndroidSupportInjectionModule.class 가 존재합니다.
 * Dagger2에서는 Application scope인 @Singleton @Provides를 이용하기 위해서는 @Singleton @Component를 이용해야 합니다.
 * Dagger2에서는 @Component는 interface or abstract class에만 붙일 수 있습니다.
 * @author 나비이쁜이
 * @since 2020.04.16
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class])
interface ApplicationComponent : AndroidInjector<GlobalApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: GlobalApplication): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(app: GlobalApplication)
}