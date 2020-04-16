package munki.db.room.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO
 * @author 나비이쁜이
 * @since 2020.04.16
 */
@Dao
interface WordDao {

    /**
     * select
     */
    @get:Query("SELECT * from search_table ORDER BY search ASC")
    val alphabetizedWords: LiveData<List<Word?>?>?

//    @Query("SELECT * from search_table ORDER BY search ASC")
//    fun getAll(): LiveData<List<Word?>?>?

    /**
     * insert
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word?)

    /**
     * delete All
     */
    @Query("DELETE FROM search_table")
    fun deleteAll()
}