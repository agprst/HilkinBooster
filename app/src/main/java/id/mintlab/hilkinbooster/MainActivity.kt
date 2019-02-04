package id.mintlab.hilkinbooster

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem
import androidx.fragment.app.Fragment


class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btmNav = findViewById<BottomNavigationView>(R.id.nav_bottom)
        btmNav.setOnNavigationItemSelectedListener(this)
        btmNav.selectedItemId = R.id.boost_menu
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.getItemId()) {
            R.id.boost_menu -> fragment = BoostFragment()
            R.id.performance_menu -> fragment = HwInfoFragment()
        }
        showFragment(fragment, R.id.fragment_container)
        return true
    }


}
