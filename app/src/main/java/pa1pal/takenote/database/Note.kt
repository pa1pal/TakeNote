package pa1pal.takenote.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo var noteTitle: String = "",
    @ColumnInfo var noteDetail: String = "",
    @ColumnInfo var timeStamp: Long = 1L
)