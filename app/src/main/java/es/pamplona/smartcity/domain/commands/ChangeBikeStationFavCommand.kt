package es.pamplona.smartcity.domain.commands

import es.pamplona.smartcity.domain.datasource.SmartCityProvider

/**
 * Created by Gio on 07/02/2018.
 */

class ChangeBikeStationFavCommand(private val id: Long, private val fav: Boolean,
                                  private val smartCityProvider: SmartCityProvider = SmartCityProvider()) :
        Command<Unit> {

    override fun execute() = smartCityProvider.changeBikeStationFav(id, fav)
}