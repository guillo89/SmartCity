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
        val plazaToros = LatLng(42.813628,-1.6363871)
        val pioXII = LatLng(42.814407, -1.652563)
        val rochapea = LatLng(42.825818, -1.649966)
        val UPNA = LatLng(42.800601, -1.638551)
        val UNAV = LatLng(42.804397, -1.663674)

        map.addMarker(MarkerOptions()
                .position(plazaToros)
                .title("Plaza de Toros"))
        map.addMarker(MarkerOptions()
                .position(pioXII)
                .title("Pio XII"))
        map.addMarker(MarkerOptions()
                .position(rochapea)
                .title("Rochapea"))
        map.addMarker(MarkerOptions()
                .position(UPNA)
                .title("Universidad Pública de Navarra"))
        map.addMarker(MarkerOptions()
                .position(UNAV)
                .title("Universidad de Navarra"))

        val builder = LatLngBounds.Builder()
                .include(plazaToros)
                .include(pioXII)
                .include(rochapea)
                .include(UPNA)
                .include(UNAV)
        val bounds = builder.build()
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        val padding = (width * 0.20).toInt()
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)
        map.moveCamera(cu)
    }

    /*
    private fun getBitmapDescriptor(id: Int): BitmapDescriptor {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val vectorDrawable = getDrawable(activity, id) as VectorDrawable
            vectorDrawable.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
            val bm = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bm)
            vectorDrawable.draw(canvas)
            return BitmapDescriptorFactory.fromBitmap(bm)
        } else {
            return BitmapDescriptorFactory.fromResource(id)
        }
    }
    */

}