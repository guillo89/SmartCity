package es.pamplona.smartcity.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.pamplona.smartcity.R
import es.pamplona.smartcity.domain.model.BikeStation
import es.pamplona.smartcity.domain.model.BikeStationList
import es.pamplona.smartcity.ui.adapters.BikeListAdapter
import kotlinx.android.synthetic.main.fragment_bike_list.*
import org.jetbrains.anko.find
import org.jetbrains.anko.toast


/**
 * Created by Asier.Guillo on 31/01/2018.
 */

class BikeListFragment : Fragment() {

    protected val stationlist: BikeStationList = loadBikeStations()

    companion object {
        val TAG: String = BikeListFragment::class.java.simpleName
        fun newInstance() = BikeListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //bikeList.layoutManager = LinearLayoutManager(activity)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater?.inflate(R.layout.fragment_bike_list, container, false)

        val bikeList = rootView?.find<RecyclerView>(R.id.bikeList)
        bikeList?.layoutManager = LinearLayoutManager(activity)
        val adapter = BikeListAdapter(stationlist) {
            activity.toast("Click en la lista")
        }
        bikeList?.adapter = adapter

        return rootView
    }

    override fun onResume() {
        super.onResume()
        loadList()
    }

    private fun loadBikeStations(): BikeStationList {
        val station = listOf(
                BikeStation(1,"Plaza de Toros", 7, 3, 0.8),
                BikeStation(2,"Pio XII", 12, 1, 1.2),
                BikeStation(2,"Rochapea", 8, 2, 2.8),
                BikeStation(2,"Universidad Pública de Navarra", 14, 2, 4.3),
                BikeStation(2,"Universidad de Navarra", 5, 8, 4.8)
        )
        return BikeStationList(station)
    }

    private fun loadList() {
        val adapter = BikeListAdapter(stationlist) {
            //startActivity<DetailActivity>(DetailActivity.ID to it.id)
            activity.toast("Click en la lista")
        }
        bikeList.adapter = adapter
    }


}