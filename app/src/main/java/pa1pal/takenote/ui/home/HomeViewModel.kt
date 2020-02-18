package pa1pal.takenote.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import pa1pal.takenote.database.AppDatabase
import pa1pal.takenote.database.Note

class HomeViewModel(context: Application) : AndroidViewModel(context) {
    private var appDatabase: AppDatabase = AppDatabase.getAppDatabase(context)

    suspend fun getAllNotes(): LiveData<List<Note>> {
        return withContext(Dispatchers.IO) {
            appDatabase.userDao().getAllNotes()
        }
    }

    fun insertNoteInDatabase(note: Note?) {
        GlobalScope.launch(Dispatchers.IO) {
            note?.let { appDatabase.userDao().insertAll(it) }
        }
    }

    suspend fun getNoteFromId(noteId: Int): Note {
        return withContext(Dispatchers.IO) {
            appDatabase.userDao().getNoteById(noteId)
        }
    }
}
