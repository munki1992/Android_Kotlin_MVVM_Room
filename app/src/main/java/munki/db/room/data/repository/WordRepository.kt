package munki.db.room.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import munki.db.room.data.WordRoomDatabase
import munki.db.room.data.WordRoomDatabase.Companion.getDatabase
import munki.db.room.data.dao.Word
import munki.db.room.data.dao.WordDao

/**
 * Model - Repository
 * @author 나비이쁜이
 * @since 2020.04.16
 */
class WordRepository(application: Application?) {

    /**
     * DAO
     */
    private val word: WordDao

    /**
     * Word List
     */
    private val mAllWords: LiveData<List<Word?>?>?

    /**
     * 생성자
     */
    init {
        // get RoomDatabase
        val database = getDatabase(application!!)

        // getDAO
        word = database!!.wordDao()

        // get All DAO List
        mAllWords = database.wordDao().alphabetizedWords
    }

    /**
     * get All DAO List - Room은 별도의 스레드에서 모든 쿼리를 실행
     * LiveData는 데이터가 변경되면 Observer에게 확인함
     */
    fun getmAllWords(): LiveData<List<Word?>?>? {
        return mAllWords
    }

    /**
     * insert - UI 스레드 사용하지마시오
     */
    fun insert(word: Word?) {
        WordRoomDatabase.databaseWriteExecutor!!.execute { this.word.insert(word) }
    }
}