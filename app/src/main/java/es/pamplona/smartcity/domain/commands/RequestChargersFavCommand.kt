package es.pamplona.smartcity.domain.commands

import es.pamplona.smartcity.domain.datasource.SmartCityProvider
import es.pamplona.smartcity.domain.model.ChargerList

/**
 * Created by Gio on 08/02/2018.
 */

class RequestChargersFavCommand(private val smartCityProvider: SmartCityProvider = SmartCityProvider()) :
        Command<ChargerList> {

    override fun execute(): ChargerList = smartCityProvider.requestChargersFav()
}