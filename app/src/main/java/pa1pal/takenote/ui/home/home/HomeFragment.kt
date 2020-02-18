package pa1pal.takenote.ui.home.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pa1pal.takenote.R
import pa1pal.takenote.database.AppDatabase
import pa1pal.takenote.ui.home.CreateNoteFragment
import pa1pal.takenote.ui.home.HomeViewModel
import pa1pal.takenote.ui.home.NoteViewFragment

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        appDatabase = context?.let { AppDatabase.getAppDatabase(it) }!!
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(HomeViewModel::class.java) }!!
        GlobalScope.launch(Dispatchers.Main) {
            var notesList = viewModel.getAllNotes().value
            if (notesList == null) {
                notesList = emptyList()
            }
            notesAdapter = NotesAdapter(notesList) {
                GlobalScope.launch(Dispatchers.IO) {
                    val note = viewModel.getNoteFromId(it)
                    activity!!.supportFragmentManager.beginTransaction()
                        .replace(R.id.container,
                            NoteViewFragment.newInstance(
                                note
                            ),
                            NoteViewFragment.TAG
                        )
                        .commitNow()
                }
            }

            viewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
                notesAdapter.setData(it)
            })
            setupRecyclerView()
        }


        fab.setOnClickListener {
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.container,
                    CreateNoteFragment.newInstance(),
                    CreateNoteFragment.TAG
                )
                .commitNow()
        }
    }

    private fun setupRecyclerView() {
        notesRecyclerView.layoutManager = LinearLayoutManager(context)
        notesRecyclerView.adapter = notesAdapter
    }
}