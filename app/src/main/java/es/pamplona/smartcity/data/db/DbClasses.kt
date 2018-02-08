package es.pamplona.smartcity.data.db

/**
 * Created by Gio on 06/02/2018.
 */

class BikeStation(val map: MutableMap<String, Any?>) {
    var _id: Long by map
    var address: String by map
    var lat: Double by map
    var lon: Double by map
    var fav: Int by map

    constructor(id: Long, address: String, lat: Double, lon: Double, fav: Int)
            : this(HashMap()) {
        this._id = id
        this.address = address
        this.lat = lat
        this.lon = lon
        this.fav = fav
    }
}

class BikeStationList(val bikeStations: List<BikeStation>) {}

class Charger(val map: MutableMap<String, Any?>) {
    var _id: Long by map
    var address: String by map
    var lat: Double by map
    var lon: Double by map
    var fav: Int by map

    constructor(id: Long, address: String, lat: Double, lon: Double, fav: Int)
            : this(HashMap()) {
        this._id = id
        this.address = address
        this.lat = lat
        this.lon = lon
        this.fav = fav
    }
}

class ChargerList(val chargers: List<Charger>) {}