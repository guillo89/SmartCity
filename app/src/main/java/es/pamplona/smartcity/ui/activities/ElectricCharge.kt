package es.pamplona.smartcity.ui.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import es.pamplona.smartcity.R
import es.pamplona.smartcity.ui.ToolbarManager
import es.pamplona.smartcity.ui.fragments.ChargerFavFragment
import es.pamplona.smartcity.ui.fragments.ChargerListFragment
import es.pamplona.smartcity.ui.fragments.ChargerMapFragment
import org.jetbrains.anko.find

/**
 * Created by Gio on 02/02/2018.
 */

class ElectricCharge : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electric_charge)

        initChargerToolbar()
        enableHomeAsUp { onBackPressed() }
        toolbarTitle = getString(R.string.toolbar_charger_title)

        val navigation = find<BottomNavigationView>(R.id.charger_navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val fragment = ChargerListFragment.Companion.newInstance()
        changeFragment(fragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.charger_navigation_map -> {
                val fragment = ChargerMapFragment.Companion.newInstance()
                changeFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.charger_navigation_list -> {
                val fragment = ChargerListFragment.Companion.newInstance()
                changeFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.charger_navigation_fav -> {
                val fragment = ChargerFavFragment.Companion.newInstance()
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