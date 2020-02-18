package pa1pal.takenote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pa1pal.takenote.ui.home.CreateNoteFragment
import pa1pal.takenote.ui.home.home.HomeFragment
import pa1pal.takenote.ui.home.NoteViewFragment

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            showHomeFragment()
        }
    }

    override fun onBackPressed() {
        when (supportFragmentManager.fragments.last()) {
            is HomeFragment -> super.onBackPressed()
            is CreateNoteFragment -> showHomeFragment()
            is NoteViewFragment -> showHomeFragment()
        }
    }

    private fun showHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment.newInstance(), HomeFragment.TAG)
            .commitNow()
    }
}
