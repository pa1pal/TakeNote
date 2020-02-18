package pa1pal.takenote.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE id=:id")
    fun getNoteById(id: Int): Note

    @Insert
    fun insertAll(vararg users: Note)

    @Delete
    fun delete(user: Note)
}