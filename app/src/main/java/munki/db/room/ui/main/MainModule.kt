package munki.db.room.ui.main

import dagger.Module
import dagger.Provides
import munki.db.room.GlobalApplication
import munki.db.room.data.repository.WordRepository

/**
 * Inject with ViewModel
 * @author 나비이쁜이
 * @since 2020.04.16
 */
@Module
class MainModule {

    @Provides
    fun createViewModel(application: GlobalApplication): MainViewModel {
        return MainViewModel(application, WordRepository(application))
    }
}