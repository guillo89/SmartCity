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
import com.google.android.gms.maps.model.MarkerOptions


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
        val latLng = LatLng(42.812526, -1.6457745)
        val zoom = 13f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
        map.addMarker(MarkerOptions().position(latLng))
    }

}