package es.pamplona.smartcity.data.db

import es.pamplona.smartcity.domain.datasource.SmartCityDataSource
import es.pamplona.smartcity.domain.model.BikeStationList
import es.pamplona.smartcity.domain.model.ChargerList
import es.pamplona.smartcity.extensions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import es.pamplona.smartcity.data.db.BikeStation as BikeStationDb
import es.pamplona.smartcity.data.db.Charger as ChargerDb

/**
 * Created by Gio on 06/02/2018.
 */

class SmartCityDb(private val smartCityDbHelper: SmartCityDbHelper = SmartCityDbHelper.instance,
                 private val dataMapper: DbDataMapper = DbDataMapper()) : SmartCityDataSource {

    override fun requestBikeStations() = smartCityDbHelper.use {
        val bikeStations = select(BikeStationTable.NAME)
                .parseList { BikeStationDb(HashMap(it)) }
        bikeStations?.let { dataMapper.convertBikeStationListToDomain(BikeStationList(it)) }
    }

    override fun requestBikeStation(id: Long) = smartCityDbHelper.use {
        val bikeStation = select(BikeStationTable.NAME).byId(id).
                parseOpt { BikeStationDb(HashMap(it)) }
        bikeStation?.let { dataMapper.convertBikeStationToDomain(it) }
    }

    override fun requestBikeStationsFav() = smartCityDbHelper.use {
        val bikeStations = select(BikeStationTable.NAME).byFav()
                .parseList { BikeStationDb(HashMap(it)) }
        bikeStations?.let { dataMapper.convertBikeStationListToDomain(BikeStationList(it)) }
    }

    override fun requestChargers() = smartCityDbHelper.use {
        val chargers = select(ChargerTable.NAME)
                .parseList { ChargerDb(HashMap(it)) }
        chargers?.let { dataMapper.convertChargerListToDomain(ChargerList(it)) }
    }

    override fun requestCharger(id: Long) = smartCityDbHelper.use {
        val charger = select(ChargerTable.NAME).byId(id).
                parseOpt { ChargerDb(HashMap(it)) }
        charger?.let { dataMapper.convertChargerToDomain(it) }
    }

    override fun requestChargersFav() = smartCityDbHelper.use {
        val chargers = select(ChargerTable.NAME).byFav()
                .parseList { ChargerDb(HashMap(it)) }
        chargers?.let { dataMapper.convertChargerListToDomain(ChargerList(it)) }
    }

    override fun changeBikeStationFav(id: Long, fav: Boolean) = smartCityDbHelper.use {
        update(BikeStationTable.NAME, BikeStationTable.FAV to when(fav){true-> 0 false-> 1}).byId(id)
                .exec()
        Unit
    }

    override fun changeChargerFav(id: Long, fav: Boolean) = smartCityDbHelper.use {
        update(ChargerTable.NAME, ChargerTable.FAV to when(fav){true-> 0 false-> 1}).byId(id)
                .exec()
        Unit
    }

    fun saveBikeStations(bikeStationList: BikeStationList) = smartCityDbHelper.use {
        clear(BikeStationTable.NAME)
        with(dataMapper.convertBikeStationListFromDomain(bikeStationList)) {
            bikeStations.forEach { insert(BikeStationTable.NAME, *it.map.toVarargArray()) }
        }
    }

    fun saveChargers(chargerList: ChargerList) = smartCityDbHelper.use {
        clear(ChargerTable.NAME)
        with(dataMapper.convertChargerListFromDomain(chargerList)) {
            chargers.forEach { insert(ChargerTable.NAME, *it.map.toVarargArray()) }
        }
    }

}
