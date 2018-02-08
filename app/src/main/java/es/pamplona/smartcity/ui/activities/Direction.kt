package es.pamplona.smartcity.ui.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import es.pamplona.smartcity.R
import es.pamplona.smartcity.ui.ToolbarManager
import es.pamplona.smartcity.ui.fragments.DirectionMapFragment
import org.jetbrains.anko.find


/**
 * Created by Gio on 05/02/2018.
 */

class Direction : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_direction)

        initChargerToolbar()
        enableHomeAsUp { onBackPressed() }
        toolbarTitle = getString(R.string.toolbar_charger_title)


        val fragment = DirectionMapFragment.Companion.newInstance()
        changeFragment(fragment)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        val tabLayout = find<TabLayout>(R.id.tabs)
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp), true)
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_map_black_24dp))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_border_black_24dp))
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_format_list_bulleted_black_24dp))
        tabLayout.addOnTabSelectedListener (object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.fragment_container, fragment)
                .commit()
    }

}
