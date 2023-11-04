package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.station.Location
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

object DistanceCalculator {
    /**
     * Calculate distance between two locations in km
     * @param
     * @param
     * @return
     */
    fun calculateDistance(userPoint: Location, stationPoint: Location): Double{
        val theta = userPoint.longitude - stationPoint.longitude
        var distance = sin(deg2rad(userPoint.latitude)) *
                sin(deg2rad(stationPoint.latitude)) +
                cos(deg2rad(userPoint.latitude)) *
                cos(deg2rad(stationPoint.latitude)) *
                cos(deg2rad(theta))

        distance = acos(distance)
        distance = rad2deg(distance)
        distance *= 60 * 1.1515

        return distance
    }

    /**
     * Transform deg number into radian
     * @param deg: number in deg
     * @return number in radian
     */
    private fun deg2rad(deg: Double): Double{
        return (deg * Math.PI / 180.0)
    }

    /**
     * Transform radian number into deg
     * @param rad: number in radian
     * @return number in deg
     */
    private fun rad2deg(rad: Double): Double{
        return (rad * 180.0 / Math.PI)
    }
}