package es.pamplona.smartcity.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.pamplona.smartcity.R
import es.pamplona.smartcity.domain.commands.ChangeBikeStationFavCommand
import es.pamplona.smartcity.domain.commands.RequestBikeStationsCommand
import es.pamplona.smartcity.ui.adapters.BikeListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread


/**
 * Created by Asier.Guillo on 31/01/2018.
 */

class BikeListFragment : Fragment() {

    companion object {
        val TAG: String = BikeListFragment::class.java.simpleName
        fun newInstance() = BikeListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater?.inflate(R.layout.fragment_bike_list, container, false)
        val bikeList = rootView?.find<RecyclerView>(R.id.bikeList)
        bikeList?.layoutManager = LinearLayoutManager(activity)
        loadBikeStations()
        return rootView
    }

    fun loadBikeStations() = doAsync {
        val result = RequestBikeStationsCommand().execute()
        uiThread {
            val adapter = BikeListAdapter(result, {}) {
                changeFav(it.id, it.fav)
                it.fav = !it.fav
                view?.find<RecyclerView>(R.id.bikeList)?.adapter?.notifyDataSetChanged()
            }
            view?.find<RecyclerView>(R.id.bikeList)?.adapter = adapter
        }
    }

    private fun changeFav(id: Long, fav: Boolean) = doAsync {
        ChangeBikeStationFavCommand(id, fav).execute()
    }

}