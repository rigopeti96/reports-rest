package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.data.request.StationRequest
import hu.aut.bme.report_rest_springdata.station.Location
import java.lang.Math.atan2
import java.lang.Math.pow
import kotlin.math.*

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
     * @param location: actual element of station from database (lat1, lon1)
     * @param stationRequest: request from user contains the actual location (lat2, lon2)
     * @return distance of the two coords
     */
    fun calcDistance(location: Location, stationRequest: StationRequest): Double{
        /*val theta: Double = location.longitude - stationRequest.longitude
        var dist =
            sin(deg2rad(location.latitude)) * sin(deg2rad(stationRequest.latitude)) + cos(location.latitude) * cos(deg2rad(stationRequest.latitude)) * cos(
                deg2rad(theta)
            )
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515 * 1.609344
        return dist*/

        val radius = 6371
        val latDistance = Math.toRadians(stationRequest.latitude - location.latitude)
        val lonDistance = Math.toRadians(stationRequest.longitude - location.longitude)

        val a = (sin(latDistance / 2).pow(2)) + (cos(location.latitude / 2) * cos(stationRequest.latitude / 2) * sin(lonDistance / 2).pow(2))
        val c = 2 * kotlin.math.atan2(sqrt(a), sqrt(1 - a))
        return radius * c * 1000
    }
}