package pa1pal.takenote.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_create_note.*

import pa1pal.takenote.R
import pa1pal.takenote.database.Note
import java.security.Timestamp

class CreateNoteFragment : Fragment() {
    companion object {
        fun newInstance() = CreateNoteFragment()
        const val TAG = "CreateNoteFragment"
    }

    private lateinit var viewModel: HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(HomeViewModel::class.java) }!!
        save.setOnClickListener {
            if (titleEditText.text?.isNotEmpty()!! && detailEditText.text?.isNotEmpty()!!) {
                val note: Note? = Note(0, titleEditText.text.toString(),
                    detailEditText.text.toString(), System.currentTimeMillis())
                viewModel.insertNoteInDatabase(note)
                moveToNoteView(note)
            } else {
                if (titleEditText.text?.isEmpty()!!) {
                    titleTextInputLayout.error = getString(R.string.enter_title)
                }

                if (detailEditText.text?.isEmpty()!!) {
                    titleTextInputLayout.error = getString(R.string.enter_note_detail)
                }
            }
        }
    }

    private fun moveToNoteView(note: Note?) {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.container, NoteViewFragment.newInstance(note!!), NoteViewFragment.TAG)
            .commitNow()
    }
}
