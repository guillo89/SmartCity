package es.pamplona.smartcity.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import es.pamplona.smartcity.ui.App

import org.jetbrains.anko.db.*

/**
 * Created by Gio on 06/02/2018.
 */

class SmartCityDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
        SmartCityDbHelper.DB_NAME, null, SmartCityDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "smartcity.db"
        val DB_VERSION = 1
        val instance by lazy { SmartCityDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(BikeStationTable.NAME, true,
                BikeStationTable.ID to INTEGER + PRIMARY_KEY,
                BikeStationTable.ADDRESS to TEXT,
                BikeStationTable.LAT to REAL,
                BikeStationTable.LON to REAL,
                BikeStationTable.FAV to INTEGER)

        db.createTable(ChargerTable.NAME, true,
                ChargerTable.ID to INTEGER + PRIMARY_KEY,
                ChargerTable.ADDRESS to TEXT,
                ChargerTable.LAT to REAL,
                ChargerTable.LON to REAL,
                ChargerTable.FAV to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(BikeStationTable.NAME, true)
        db.dropTable(ChargerTable.NAME, true)
        onCreate(db)
    }

}