package pa1pal.takenote.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pa1pal.takenote.database.AppDatabase
import pa1pal.takenote.database.Note

class HomeViewModel(context: Application) : AndroidViewModel(context) {
    private var appDatabase: AppDatabase = AppDatabase.getAppDatabase(context)

    fun getAllNotes(): LiveData<List<Note>> {
        return appDatabase.userDao().getAllNotes()
    }

    fun insertNoteInDatabase(note: Note?) {
        note?.let { appDatabase.userDao().insertAll(it) }
    }

    fun getNoteFromId(noteId: Int): Note {
        return appDatabase.userDao().getNoteById(noteId)
    }
}
