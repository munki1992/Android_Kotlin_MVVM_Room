package munki.db.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import munki.db.room.data.dao.Word
import munki.db.room.data.dao.WordDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Room Database Backend
 * @author 나비이쁜이
 * @since 2020.04.16
 */
@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    /**
     * DAO
     */
    abstract fun wordDao(): WordDao

    /**
     * static
     */
    companion object {

        /**
         * instance
         */
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        /**
         * ExecutorService
         */
        val databaseWriteExecutor: ExecutorService? = Executors.newFixedThreadPool(4)

        /**
         * getDatabase
         */
        @JvmStatic
        fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder(context.applicationContext, WordRoomDatabase::class.java, "word_database")
                                .addCallback(sRoomDatabaseCallback)
                                .build()
                    }
                }
            }
            return INSTANCE
        }

        /**
         * callback - execute
         */
        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                /**
                 * 앱 재시작을 통해 데이터를 유지할 것인가?
                 * 아래 코드 주석을 풀면 앱 종료후 재실행마다 저장한 DB를 모두 삭제함
                 */
                databaseWriteExecutor?.execute {
                    INSTANCE!!.wordDao().deleteAll()
                }
            }
        }
    }
}