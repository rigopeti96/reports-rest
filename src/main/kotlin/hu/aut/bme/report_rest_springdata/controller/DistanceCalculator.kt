package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.data.request.StationRequest
import hu.aut.bme.report_rest_springdata.station.Location
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

object DistanceCalculator {

    /**
     * This function converts decimal degrees to radians
     */
    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    /**
     * This function converts radians to decimal degrees
     */
    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }

    /**
     * Calculate distance between two coords
     * @param station: actual element of station from database (lat1, lon1)
     * @param stationRequest: request from user contains the actual location (lat2, lon2)
     * @return distance of the two coords
     */
    fun calcDistance(location: Location, stationRequest: StationRequest): Double{
        val theta: Double = location.longitude - stationRequest.longitude
        var dist =
            sin(deg2rad(location.latitude)) * sin(deg2rad(stationRequest.latitude)) + cos(location.latitude) * cos(deg2rad(stationRequest.latitude)) * cos(
                deg2rad(theta)
            )
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515 * 1.609344
        return dist
    }
}