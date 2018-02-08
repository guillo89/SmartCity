package es.pamplona.smartcity.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import es.pamplona.smartcity.domain.commands.RequestBikeStationsCommand
import es.pamplona.smartcity.domain.commands.RequestChargersCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Gio on 05/02/2018.
 */

class DirectionMapFragment : SupportMapFragment(), OnMapReadyCallback {

    companion object {
        val TAG: String = DirectionMapFragment::class.java.simpleName
        fun newInstance() = DirectionMapFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        getMapAsync(this)
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        loadMarkers(map)
    }

    private fun loadMarkers(map: GoogleMap) = doAsync {
        val bikeStations = RequestBikeStationsCommand().execute()
        val chargers = RequestChargersCommand().execute()
        uiThread {
            val builder = LatLngBounds.Builder()
            chargers.chargerList.forEach {
                val position = LatLng(it.lat, it.lon)
                map.addMarker(MarkerOptions()
                        .position(position)
                        .title(it.address))
                builder.include(position)
            }
            bikeStations.bikeStationList.forEach {
                val position = LatLng(it.lat, it.lon)
                map.addMarker(MarkerOptions()
                        .position(position)
                        .title(it.address))
                builder.include(position)
            }
            val bounds = builder.build()
            val width = resources.displayMetrics.widthPixels
            val height = resources.displayMetrics.heightPixels
            val padding = (width * 0.20).toInt()
            val cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)
            map.moveCamera(cu)
        }
    }

}