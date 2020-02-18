package pa1pal.takenote.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_note_view.*

import pa1pal.takenote.R
import pa1pal.takenote.database.Note

class NoteViewFragment(val note: Note) : Fragment() {
    companion object {
        fun newInstance(it: Note) = NoteViewFragment(it)
        val TAG = "NoteViewFragment"
    }

    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(HomeViewModel::class.java) }!!

        noteViewTitle.text = note.noteTitle
        noteViewDetail.text = note.noteDetail
    }
}
