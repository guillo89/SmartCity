package es.pamplona.smartcity.data.db

import es.pamplona.smartcity.domain.model.BikeStation
import es.pamplona.smartcity.domain.model.BikeStationList
import es.pamplona.smartcity.domain.model.Charger
import es.pamplona.smartcity.domain.model.ChargerList
import es.pamplona.smartcity.data.db.BikeStation as BikeStationDb
import es.pamplona.smartcity.data.db.BikeStationList as BikeStationListDb
import es.pamplona.smartcity.data.db.Charger as ChargerDb
import es.pamplona.smartcity.data.db.ChargerList as ChargerListDb

/**
 * Created by Gio on 06/02/2018.
 */

class DbDataMapper {

    fun convertBikeStationFromDomain(bikeStation: BikeStation) = with(bikeStation) {
        BikeStationDb(id, address, lat, lon, when(fav){true-> 1 false-> 0})
    }

    fun convertBikeStationListFromDomain(bikeStations: BikeStationList) = with(bikeStations) {
        val bikeStationList = bikeStations.bikeStationList.map { convertBikeStationFromDomain(it) }
        BikeStationListDb(bikeStationList)
    }

    fun convertChargerFromDomain(charger: Charger) = with(charger) {
        ChargerDb(id, address, lat, lon, when(fav){true-> 1 false-> 0})
    }

    fun convertChargerListFromDomain(chargers: ChargerList) = with(chargers) {
        val chargersList = chargers.chargerList.map { convertChargerFromDomain(it) }
        ChargerListDb(chargersList)
    }

    fun convertBikeStationToDomain(bikeStation: BikeStationDb) = with(bikeStation) {
        BikeStation(_id, address, lat, lon, when(fav){1-> true else-> false}, null, null)
    }

    fun convertBikeStationListToDomain(bikeStations: BikeStationListDb) = with(bikeStations) {
        val bikeStationList = bikeStations.bikeStations.map { convertBikeStationToDomain(it) }
        BikeStationList(bikeStationList)
    }

    fun convertChargerToDomain(charger: ChargerDb) = with(charger) {
        Charger(_id, address, lat, lon, when(fav){1-> true else-> false}, null, null)
    }

    fun convertChargerListToDomain(chargers: ChargerListDb) = with(chargers) {
        val chargersList = chargers.chargers.map { convertChargerToDomain(it) }
        ChargerList(chargersList)
    }

}