package es.pamplona.smartcity.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.pamplona.smartcity.R

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
        return rootView
    }
}