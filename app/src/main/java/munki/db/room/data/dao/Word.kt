package munki.db.room.data.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity
 * [@Entity]        - 클래스를 엔티티로 어노테이션 | 클래스 이름이 아닌 경우 테이블 이름을 제공
 * [@PrimaryKey]    - 기본 키를 식별
 * [@ColumnInfo]    - Column Name이 변수 이름과 다른 경우에는 반드시 Column Name을 제공
 * @author 나비이쁜이
 * @since 2020.04.16
 */
@Entity(tableName = "search_table")
class Word (
    @field:ColumnInfo(name = "search")
    @field:PrimaryKey
    val searchWord: String
)