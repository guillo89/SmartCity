package es.pamplona.smartcity.domain.model

/**
 * Created by Asier.Guillo on 01/02/2018.
 */

data class BikeStationList(var bikeStationList: List<BikeStation>) {
    val size: Int get() = bikeStationList.size
    operator fun get(position: Int) = bikeStationList[position]
}

data class ChargerList(var chargerList: List<Charger>) {
    val size: Int get() = chargerList.size
    operator fun get(position: Int) = chargerList[position]
}

data class BikeStation(val id: Long, val address: String, val lat: Double, val lon: Double,
                       var fav: Boolean, val status: List<Boolean>?, val distance: Double?)

data class Charger(val id: Long, val address: String, val lat: Double, val lon: Double,
                   var fav: Boolean, val status: Boolean?, val distance: Double?)