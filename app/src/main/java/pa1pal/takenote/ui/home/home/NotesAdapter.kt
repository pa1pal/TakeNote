package pa1pal.takenote.ui.home.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*
import pa1pal.takenote.R
import pa1pal.takenote.database.Note

class NotesAdapter(
    private var notesList: List<Note>, val callback: (position: Int) -> Unit
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_view, parent, false
        ))
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    fun setData(it: List<Note>?) {
        if (it != null) {
            notesList = it
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            itemView.note_title.text = note.noteTitle
            itemView.note_details.text = note.noteDetail

            itemView.setOnClickListener {
                callback.invoke(note.id)
            }
        }
    }
}