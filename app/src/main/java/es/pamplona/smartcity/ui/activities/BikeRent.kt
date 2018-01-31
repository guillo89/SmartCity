package es.pamplona.smartcity.ui.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import es.pamplona.smartcity.R
import es.pamplona.smartcity.ui.ToolbarManager
import es.pamplona.smartcity.ui.fragments.BikeFavFragment
import es.pamplona.smartcity.ui.fragments.BikeListFragment
import es.pamplona.smartcity.ui.fragments.BikeMapFragment
import org.jetbrains.anko.find

class BikeRent : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike_rent)

        initBikeToolbar()
        enableHomeAsUp { onBackPressed() }
        toolbarTitle = getString(R.string.toolbar_bike_title)

        val navigation = find<BottomNavigationView>(R.id.bike_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = BikeMapFragment.Companion.newInstance()
        changeFragment(fragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.bike_navigation_map -> {
                val fragment = BikeMapFragment.Companion.newInstance()
                changeFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bike_navigation_list -> {
                val fragment = BikeListFragment.Companion.newInstance()
                changeFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.bike_navigation_fav -> {
                val fragment = BikeFavFragment.Companion.newInstance()
                changeFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

}
