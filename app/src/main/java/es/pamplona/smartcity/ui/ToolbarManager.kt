package es.pamplona.smartcity.ui


import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.Toolbar
import es.pamplona.smartcity.R
import es.pamplona.smartcity.extensions.ctx

/**
 * Created by Asier.Guillo on 31/01/2018.
 */

interface ToolbarManager {

    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initHomeToolbar() {
        toolbar.inflateMenu(R.menu.menu_home_toolbar)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                //R.id.toolbar_settings -> App.instance.toast("Ajustes")
                //else -> App.instance.toast("Opcion desconocida")
            }
            true
        }
    }

    fun initBikeToolbar() {
        toolbar.inflateMenu(R.menu.menu_home_toolbar)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                //R.id.toolbar_settings -> App.instance.toast("Ajustes")
                //else -> App.instance.toast("Opcion desconocida")
            }
            true
        }
    }

    fun initChargerToolbar() {
        toolbar.inflateMenu(R.menu.menu_home_toolbar)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
            //R.id.toolbar_settings -> App.instance.toast("Ajustes")
            //else -> App.instance.toast("Opcion desconocida")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }
        toolbar.setNavigationOnClickListener { up() }
    }

    /*
    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
    */
}