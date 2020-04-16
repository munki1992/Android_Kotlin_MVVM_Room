package munki.db.room.ui.main

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.LiveData
import munki.db.room.GlobalApplication
import munki.db.room.data.dao.Word
import munki.db.room.data.repository.WordRepository
import munki.db.room.ui.base.BaseViewModel

/**
 * MainViewModel
 * @author 나비이쁜이
 * @since 2020.04.16
 */
class MainViewModel internal constructor(application: GlobalApplication, private val mRepository: WordRepository) : BaseViewModel<MainNavigator>(application) {

    /**
     * LiveData - Room Word
     */
    private var mAllWords : LiveData<List<Word?>?>?
    fun getAllWords(): LiveData<List<Word?>?>? {
        if (mAllWords == null)
            mAllWords = object : LiveData<List<Word?>?>() {}
        return mAllWords
    }

    /**
     * 초기화
     */
    init { mAllWords = this.mRepository.getmAllWords() }

    /************************************************************************************************************************************************/

    /* Action */

    /**
     * insert DB
     */
    private fun insert(word: Word) {
        mRepository.insert(word)
    }

    /**
     * Edittext Action done
     */
    fun onEditorAction(v: TextView?, actionId: Int?, event: KeyEvent?) : Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            insert(Word(navigation!!.getString()))
            return true
        }
        return false
    }
}