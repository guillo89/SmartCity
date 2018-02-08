package es.pamplona.smartcity.domain.commands

import es.pamplona.smartcity.domain.datasource.SmartCityProvider
import es.pamplona.smartcity.domain.model.BikeStationList

/**
 * Created by Gio on 07/02/2018.
 */

class RequestBikeStationsCommand(private val smartCityProvider: SmartCityProvider = SmartCityProvider()) :
        Command<BikeStationList> {

    override fun execute(): BikeStationList = smartCityProvider.requestBikeStations()
}