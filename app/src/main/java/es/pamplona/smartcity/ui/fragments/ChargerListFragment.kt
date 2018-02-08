package es.pamplona.smartcity.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.pamplona.smartcity.R
import es.pamplona.smartcity.domain.commands.ChangeChargerFavCommand
import es.pamplona.smartcity.domain.commands.RequestChargersCommand
import es.pamplona.smartcity.ui.adapters.ChargerListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

/**
 * Created by Gio on 02/02/2018.
 */

class ChargerListFragment : Fragment() {

    companion object {
        val TAG: String = ChargerListFragment::class.java.simpleName
        fun newInstance() = ChargerListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater?.inflate(R.layout.fragment_charger_list, container, false)
        val chargerList = rootView?.find<RecyclerView>(R.id.chargerList)
        chargerList?.layoutManager = LinearLayoutManager(activity)
        loadChargers()
        return rootView
    }

    fun loadChargers() = doAsync {
        val result = RequestChargersCommand().execute()
        uiThread {
            val adapter = ChargerListAdapter(result, {}) {
                changeFav(it.id, it.fav)
                it.fav = !it.fav
                view?.find<RecyclerView>(R.id.chargerList)?.adapter?.notifyDataSetChanged()
            }
            view?.find<RecyclerView>(R.id.chargerList)?.adapter = adapter
        }
    }

    private fun changeFav(id: Long, fav: Boolean) = doAsync {
        ChangeChargerFavCommand(id, fav).execute()
    }

}