package munki.db.room.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import munki.db.room.GlobalApplication
import javax.inject.Singleton

/**
 * AppComponent의 Module 제공
 * Module은 class에만 붙이며 Provides는 반드시 Module class 안에 선언된 메소드에만 사용
 * @author 나비이쁜이
 * @since 2020.04.16
 */
@Module
class AppModule {

    /**
     * ApplicationComponent에 기본적으로 사용되는 application
     */
    @Provides
    @Singleton
    fun provideContext(application: GlobalApplication): Context {
        return application
    }
}