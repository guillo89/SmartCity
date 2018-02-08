package es.pamplona.smartcity.data.mockup

import es.pamplona.smartcity.data.db.SmartCityDb
import es.pamplona.smartcity.domain.datasource.SmartCityDataSource
import es.pamplona.smartcity.domain.model.BikeStation
import es.pamplona.smartcity.domain.model.BikeStationList
import es.pamplona.smartcity.domain.model.Charger
import es.pamplona.smartcity.domain.model.ChargerList

/**
 * Created by Gio on 07/02/2018.
 */

class SmartCityMockup(private val smartCityDb: SmartCityDb = SmartCityDb()) : SmartCityDataSource {

    override fun requestBikeStations(): BikeStationList? {
        val status1 = listOf(true, false, true, true, true, false, false, true, true, false, true, false, true, false, false)
        val status2 = listOf(false, false, false, true, false, false, true, true, false, false, true, true, true, false, true)
        val status3 = listOf(true, true, false, false, true, false, true, false, false, true, true, true, true, false, true, true)
        val status4 = listOf(false, false, true, false, false, false, false, false, false, false, false, false, false, false)
        val status5 = listOf(true, true, true, true, true, true, true, false, true, true)
        val stations = listOf(
                BikeStation(1,"Plaza de Toros", 42.813628, -1.6363871, true, status1, 0.8),
                BikeStation(2,"Pio XII", 42.814407, -1.652563, false, status2, 1.2),
                BikeStation(3,"Rochapea", 42.825818, -1.649966, false, status3, 2.8),
                BikeStation(4,"Universidad Pública de Navarra", 42.800601, -1.638551, true, status4, 4.3),
                BikeStation(5,"Universidad de Navarra", 42.804397, -1.663674, false, status5, 4.8)
        )
        val stationList = BikeStationList(stations)
        smartCityDb.saveBikeStations(stationList)
        return stationList
    }

    override fun requestBikeStation(id: Long) = throw  UnsupportedOperationException()

    override fun requestBikeStationsFav() = throw  UnsupportedOperationException()

    override fun requestChargers(): ChargerList? {
        val chargers = listOf(
                Charger(1,"Calle Esquiroz", 42.8080845, -1.6491322, false, true, 1.5),
                Charger(2,"Calle Esquiroz", 42.807923, -1.6492364, false, false, 1.5),
                Charger(3,"Calle Arcadio", 42.811947, -1.664236, false, true, 0.6),
                Charger(4,"Avenida San Ignacio", 42.813969, -1.643062, false, true, 1.3)
        )
        val chargerList = ChargerList(chargers)
        smartCityDb.saveChargers(chargerList)
        return chargerList
    }

    override fun requestCharger(id: Long) = throw  UnsupportedOperationException()

    override fun requestChargersFav() = throw  UnsupportedOperationException()

    override fun changeBikeStationFav(id: Long, fav: Boolean) = throw  UnsupportedOperationException()

    override fun changeChargerFav(id: Long, fav: Boolean) = throw  UnsupportedOperationException()

}