package munki.db.room

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication
import munki.db.room.di.component.ApplicationComponent
import munki.db.room.di.component.DaggerApplicationComponent

/**
 * Activity에서 공통적으로 적용되는 상위 MultiDexApplication
 * @author 나비이쁜이
 * @since 2020.04.16
 */
class GlobalApplication : DaggerApplication() , HasAndroidInjector {

    /**
     * ContributesAndroidInjector를 사용하기 위한 Injector
     */
    override fun applicationInjector(): ApplicationComponent? {
        return DaggerApplicationComponent.builder().application(this).build()
    }

    /**
     * attachBaseContext
     */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        // MultiDex init
        MultiDex.install(this)
    }
}