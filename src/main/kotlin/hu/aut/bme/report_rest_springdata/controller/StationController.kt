package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.station.Station
import hu.aut.bme.report_rest_springdata.repository.StationRepository
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import hu.aut.bme.report_rest_springdata.request.StationRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin


/**
 * Station's endpoint class
 */
@RestController
@RequestMapping("/api/station")
class StationController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var stationRepository: StationRepository

    /**
     * Send the stations' data which are closer than stationRequest.distance attribute
     * @param stationRequest: Request from user contains position data and distance
     * @return list of station which distance's is lower than the radius
     */
    @GetMapping("/getStations")
    fun getStations(stationRequest: StationRequest): ArrayList<Station>{
        val locations = stationRepository.findAll()
        val responseStations = ArrayList<Station>()

        for(i in 0 until locations.size){
            if(calcDistance(locations[i], stationRequest) <= stationRequest.distance){
                responseStations.add(locations[i])
            }
        }

        return responseStations
    }

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
    private fun calcDistance(station: Station, stationRequest: StationRequest): Double{
        val theta: Double = station.longitude - stationRequest.longitude
        var dist =
            sin(deg2rad(station.latitude)) * sin(deg2rad(stationRequest.latitude)) + cos(station.latitude) * cos(deg2rad(stationRequest.latitude)) * cos(
                deg2rad(theta)
            )
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515 * 1.609344
        return dist
    }
}