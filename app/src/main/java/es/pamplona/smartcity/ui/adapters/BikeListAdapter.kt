package es.pamplona.smartcity.ui.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.pamplona.smartcity.R
import es.pamplona.smartcity.domain.model.BikeStation
import es.pamplona.smartcity.domain.model.BikeStationList
import es.pamplona.smartcity.extensions.ctx
import es.pamplona.smartcity.ui.App
import kotlinx.android.synthetic.main.item_bike_list.view.*

/**
 * Created by Asier.Guillo on 01/02/2018.
 */

class BikeListAdapter(private var bikeStation: BikeStationList,
                      private val itemClick: (BikeStation) -> Unit,
                      private val favClick: (BikeStation) -> Unit) :
        RecyclerView.Adapter<BikeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_bike_list, parent, false)
        return ViewHolder(view, itemClick, favClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindBikeStation(bikeStation[position])
    }

    override fun getItemCount() = bikeStation.size

    class ViewHolder(view: View,
                     private val itemClick: (BikeStation) -> Unit,
                     private val favClick: (BikeStation) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindBikeStation(bikeStation: BikeStation) {
            with(bikeStation) {
                itemView.place_bike_list.text = address
                when(fav) {
                    true -> itemView.fav_bike_list.setImageDrawable(ContextCompat.getDrawable(App.instance, R.drawable.ic_favorite_black_24dp))
                    false -> itemView.fav_bike_list.setImageDrawable(ContextCompat.getDrawable(App.instance, R.drawable.ic_favorite_border_black_24dp))
                }
                if(status != null) {
                    itemView.status_bike_list.visibility = View.VISIBLE
                    val available = status.count{ it == true }
                    val free = status.count{ it == false }
                    itemView.green_bike_list.text = "${App.instance.resources.getQuantityString(R.plurals.item_bike_available, available , available)}"
                    itemView.gray_bike_list.text = "${App.instance.resources.getQuantityString(R.plurals.item_bike_free, free , free)}"
                }
                if(distance != null) {
                    itemView.distance_bike_list.visibility = View.VISIBLE
                    itemView.distance_bike_list.text = "a $distance km"
                }
                itemView.fav_bike_list.setOnClickListener { favClick(this) }
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }

}