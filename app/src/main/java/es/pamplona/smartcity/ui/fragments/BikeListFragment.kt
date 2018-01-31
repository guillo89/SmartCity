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

class BikeListFragment : Fragment() {

    companion object {
        val TAG: String = BikeListFragment::class.java.simpleName
        fun newInstance() = BikeListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater?.inflate(R.layout.fragment_bike_list, container, false)
        return rootView
    }
}