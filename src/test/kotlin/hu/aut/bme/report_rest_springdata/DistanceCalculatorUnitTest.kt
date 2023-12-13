package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.controller.DistanceCalculator
import hu.aut.bme.report_rest_springdata.station.Location
import org.junit.Test


class DistanceCalculatorUnitTest {
    private val userLocation = Location(47.469241, 19.027637)
    private val stationLocation = Location(47.470706, 19.028699)
    private val expectedDistance = 0.1815

    /**
     * Calculate distance between two given points and check if the distance is the expected distance value
     * Expected result: the calculation result and the given distance is equal
     */
    @Test
    fun calculateDistanceBetweenTwoPoint(){
        assert(DistanceCalculator.calculateDistance(userLocation, stationLocation) == expectedDistance)
    }

    /**
     * Calculate distance between two given point and a distance between userLocation and a test location
     * Expected result: given points' distance is longer
     */
    @Test
    fun calculateDistanceAndCheckIfPointIsInRange(){
        val testPoint = Location(47.469756, 19.027787)
        val testDistance = DistanceCalculator.calculateDistance(userLocation, testPoint)

        assert(testDistance < DistanceCalculator.calculateDistance(userLocation, stationLocation))
    }

    /**
     * Calculate distance between two given point and a distance between userLocation and a test location
     * Expected result: given points' distance is shorter
     */
    @Test
    fun calculateDistanceAndCheckIfPointIsOutOfRange(){
        val testPoint = Location(47.471083, 19.029579)
        val testDistance = DistanceCalculator.calculateDistance(userLocation, testPoint)

        assert(testDistance > DistanceCalculator.calculateDistance(userLocation, stationLocation))
    }
}