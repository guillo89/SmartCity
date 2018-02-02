package es.pamplona.smartcity.domain.model

/**
 * Created by Asier.Guillo on 01/02/2018.
 */

data class BikeStationList(val bikeStationList: List<BikeStation>) {
    val size: Int get() = bikeStationList.size
    operator fun get(position: Int) = bikeStationList[position]
}

data class BikeStation(val id: Long, val place: String, val available: Int, val free: Int, val distance: Double)