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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Asier.Guillo on 31/01/2018.
 */

class BikeMapFragment : SupportMapFragment(), OnMapReadyCallback {

    companion object {
        val TAG: String = BikeMapFragment::class.java.simpleName
        fun newInstance() = BikeMapFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        getMapAsync(this)
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        loadBikeStations(map)
    }

    private fun loadBikeStations(map: GoogleMap) = doAsync {
        val result = RequestBikeStationsCommand().execute()
        uiThread {
            val builder = LatLngBounds.Builder()
            result.bikeStationList.forEach {
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