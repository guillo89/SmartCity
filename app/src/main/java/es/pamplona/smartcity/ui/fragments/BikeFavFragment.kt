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
import es.pamplona.smartcity.domain.commands.RequestBikeStationsFavCommand
import es.pamplona.smartcity.ui.adapters.BikeListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

/**
 * Created by Asier.Guillo on 31/01/2018.
 */

class BikeFavFragment : Fragment() {

    companion object {
        val TAG: String = BikeFavFragment::class.java.simpleName
        fun newInstance() = BikeFavFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater?.inflate(R.layout.fragment_bike_fav, container, false)
        val bikeList = rootView?.find<RecyclerView>(R.id.bikeListFav)
        bikeList?.layoutManager = LinearLayoutManager(activity)
        loadBikeStationsFav()
        return rootView
    }


    fun loadBikeStationsFav() = doAsync {
        val result = RequestBikeStationsFavCommand().execute()
        uiThread {
            val adapter = BikeListAdapter(result, {}) {
                changeFav(it.id, it.fav)
                it.fav = !it.fav
                result.bikeStationList = result.bikeStationList.filter { it.fav }
                view?.find<RecyclerView>(R.id.bikeListFav)?.adapter?.notifyDataSetChanged()
            }
            view?.find<RecyclerView>(R.id.bikeListFav)?.adapter = adapter
        }
    }

    private fun changeFav(id: Long, fav: Boolean) = doAsync {
        ChangeBikeStationFavCommand(id, fav).execute()
    }

}