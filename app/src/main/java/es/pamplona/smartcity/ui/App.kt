package es.pamplona.smartcity.ui

import android.app.Application
import es.pamplona.smartcity.extensions.DelegatesExt

/**
 * Created by Asier.Guillo on 31/01/2018.
 */

class App: Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}