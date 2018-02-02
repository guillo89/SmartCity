package es.pamplona.smartcity.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.pamplona.smartcity.R
import es.pamplona.smartcity.domain.model.BikeStation
import es.pamplona.smartcity.domain.model.BikeStationList
import es.pamplona.smartcity.extensions.ctx
import kotlinx.android.synthetic.main.item_bike_list.view.*

/**
 * Created by Asier.Guillo on 01/02/2018.
 */

class BikeListAdapter(private val bikeStation: BikeStationList,
                      private val itemClick: (BikeStation) -> Unit) :
        RecyclerView.Adapter<BikeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_bike_list, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindBikeStation(bikeStation[position])
    }

    override fun getItemCount() = bikeStation.size

    class ViewHolder(view: View, private val itemClick: (BikeStation) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindBikeStation(bikeStation: BikeStation) {
            with(bikeStation) {
                itemView.place_bike_list.text = place
                itemView.green_bike_list.text = "$available disponibles"
                itemView.gray_bike_list.text = "$free libres"
                itemView.distance_bike_list.text = "a $distance km"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

}