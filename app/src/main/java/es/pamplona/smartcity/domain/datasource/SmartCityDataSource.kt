package es.pamplona.smartcity.domain.datasource

import es.pamplona.smartcity.domain.model.BikeStation
import es.pamplona.smartcity.domain.model.BikeStationList
import es.pamplona.smartcity.domain.model.Charger
import es.pamplona.smartcity.domain.model.ChargerList

/**
 * Created by Gio on 06/02/2018.
 */

interface SmartCityDataSource {
    fun requestBikeStations(): BikeStationList?
    fun requestBikeStation(id: Long): BikeStation?
    fun requestBikeStationsFav(): BikeStationList?
    fun requestChargers(): ChargerList?
    fun requestCharger(id: Long): Charger?
    fun requestChargersFav(): ChargerList?
    fun changeBikeStationFav(id: Long, fav: Boolean)
    fun changeChargerFav(id: Long, fav: Boolean)
}