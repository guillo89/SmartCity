package es.pamplona.smartcity.domain.datasource

import es.pamplona.smartcity.data.db.SmartCityDb
import es.pamplona.smartcity.data.mockup.SmartCityMockup
import es.pamplona.smartcity.domain.model.BikeStation
import es.pamplona.smartcity.domain.model.BikeStationList
import es.pamplona.smartcity.domain.model.Charger
import es.pamplona.smartcity.domain.model.ChargerList
import es.pamplona.smartcity.extensions.firstResult

/**
 * Created by Gio on 07/02/2018.
 */

class SmartCityProvider(private val sources: List<SmartCityDataSource> = SmartCityProvider.SOURCES,
                        private val dbsource: List<SmartCityDataSource> = SmartCityProvider.DBSOURCE) {

    companion object {
        val SOURCES by lazy { listOf(SmartCityDb(), SmartCityMockup()) }
        val DBSOURCE by lazy { listOf(SmartCityDb()) }
    }

    fun requestBikeStations(): BikeStationList = requestToSources {
        val res = it.requestBikeStations()
        if (res != null && res.bikeStationList.isNotEmpty()) res else null
    }

    fun requestBikeStation(id: Long): BikeStation = requestToSources { it.requestBikeStation(id) }

    fun requestBikeStationsFav(): BikeStationList = requestToDBSource {
        val res = it.requestBikeStationsFav()
        if (res != null && res.bikeStationList.isNotEmpty()) res else null
    }

    fun requestCharger(id: Long): Charger = requestToSources { it.requestCharger(id) }

    fun requestChargers(): ChargerList = requestToSources {
        val res = it.requestChargers()
        if (res != null && res.chargerList.isNotEmpty()) res else null
    }

    fun requestChargersFav(): ChargerList = requestToDBSource {
        val res = it.requestChargersFav()
        if (res != null && res.chargerList.isNotEmpty()) res else null
    }

    fun changeBikeStationFav(id: Long, fav: Boolean) = requestToDBSource { it.changeBikeStationFav(id, fav) }

    fun changeChargerFav(id: Long, fav: Boolean) = requestToDBSource { it.changeChargerFav(id, fav) }

    private fun <T : Any> requestToSources(f: (SmartCityDataSource) -> T?): T = sources.firstResult { f(it) }

    private fun <T : Any> requestToDBSource(f: (SmartCityDataSource) -> T?): T = dbsource.firstResult { f(it) }

}