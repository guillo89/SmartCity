package es.pamplona.smartcity.domain.commands

import es.pamplona.smartcity.domain.datasource.SmartCityProvider
import es.pamplona.smartcity.domain.model.BikeStation

/**
 * Created by Gio on 07/02/2018.
 */

class RequestBikeStationCommand(private val id: Long,
                                private val smartCityProvider: SmartCityProvider = SmartCityProvider()) :
        Command<BikeStation> {

    override fun execute() = smartCityProvider.requestBikeStation(id)
}