package es.pamplona.smartcity.domain.commands

import es.pamplona.smartcity.domain.datasource.SmartCityProvider
import es.pamplona.smartcity.domain.model.Charger

/**
 * Created by Gio on 07/02/2018.
 */

class RequestChargerCommand(private val id: Long,
                            private val smartCityProvider: SmartCityProvider = SmartCityProvider()) :
        Command<Charger> {

    override fun execute() = smartCityProvider.requestCharger(id)
}