package es.pamplona.smartcity.ui.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.pamplona.smartcity.R
import es.pamplona.smartcity.domain.model.Charger
import es.pamplona.smartcity.domain.model.ChargerList
import es.pamplona.smartcity.extensions.ctx
import es.pamplona.smartcity.ui.App
import kotlinx.android.synthetic.main.item_charger_list.view.*

/**
 * Created by Gio on 08/02/2018.
 */

class ChargerListAdapter(private var charger: ChargerList,
                         private val itemClick: (Charger) -> Unit,
                         private val favClick: (Charger) -> Unit) :
        RecyclerView.Adapter<ChargerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_charger_list, parent, false)
        return ViewHolder(view, itemClick, favClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCharger(charger[position])
    }

    override fun getItemCount() = charger.size

    class ViewHolder(view: View,
                     private val itemClick: (Charger) -> Unit,
                     private val favClick: (Charger) -> Unit)
        : RecyclerView.ViewHolder(view) {

        fun bindCharger(charger: Charger) {
            with(charger) {
                itemView.place_charger_list.text = address
                when(fav) {
                    true -> itemView.fav_charger_list.setImageDrawable(ContextCompat.getDrawable(App.instance, R.drawable.ic_favorite_black_24dp))
                    false -> itemView.fav_charger_list.setImageDrawable(ContextCompat.getDrawable(App.instance, R.drawable.ic_favorite_border_black_24dp))
                }
                if(status != null) {
                    itemView.status_charger_list.visibility = View.VISIBLE
                    when(status) {
                        true -> {
                            itemView.status_image_charger_list.setImageDrawable(ContextCompat.getDrawable(App.instance, R.drawable.ic_circle_green))
                            itemView.status_text_charger_list.text = App.instance.getString(R.string.item_charger_free)
                        }
                        false -> {
                            itemView.status_image_charger_list.setImageDrawable(ContextCompat.getDrawable(App.instance, R.drawable.ic_circle_grey))
                            itemView.status_text_charger_list.text = App.instance.getString(R.string.item_charger_occupied)
                        }
                    }

                }
                if(distance != null) {
                    itemView.distance_charger_list.visibility = View.VISIBLE
                    itemView.distance_charger_list.text = "a $distance km"
                }
                itemView.fav_charger_list.setOnClickListener { favClick(this) }
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }

}