package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.collections.Station
import hu.aut.bme.report_rest_springdata.collections.Stops
import hu.aut.bme.report_rest_springdata.repository.StationRepository
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import hu.aut.bme.report_rest_springdata.data.request.StationRequest
import hu.aut.bme.report_rest_springdata.data.response.StationList
import hu.aut.bme.report_rest_springdata.repository.StopRepository
import hu.aut.bme.report_rest_springdata.station.Location
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
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
    private lateinit var stopStationRepository: StopRepository

    /**
     * Send all of the stations'
     * @return list of all stations
     */
    @GetMapping("/getAllStations")
    @PreAuthorize("hasRole('USER')")
    fun getAllStations(): MutableList<Stops?> {
        return stopStationRepository.findAll()
    }

    /**
     *
     * @param latitude: request bod
     * @param longitude: request bod
     * @param distance: request bod
     * @return
     */
    @GetMapping("/getStationsByDistance")
    @PreAuthorize("hasRole('USER')")
    fun getStationsByDistance(
        @RequestParam(name = "latitude") latitude: Double,
        @RequestParam(name = "longitude") longitude: Double,
        @RequestParam(name = "distance") distance: Double
    ): ResponseEntity<*> {

        val stationRequest = StationRequest(
            latitude,
            longitude,
            distance
        )
        logger.info("station request values: ${stationRequest.latitude}, ${stationRequest.longitude}, ${stationRequest.distance}")
        val userLocation = Location(stationRequest.latitude, stationRequest.longitude)
        val stopList = stopStationRepository.findAll()

        val responseList = ArrayList<Stops>()
        if(stopList.size > 0){
            for(i in 0 until stopList.size){
                val calcDist = DistanceCalculator.calculateDistance(userLocation, Location(stopList[i]!!.stop_lat, stopList[i]!!.stop_lon))
                if(calcDist < stationRequest.distance){
                    responseList.add(stopList[i]!!)
                    logger.info("Station found! Station name: ${stopList[i]!!.stop_name}, distance: $calcDist")
                }
            }
        }

        return ResponseEntity.ok().body(StationList(responseList))

    }

    companion object {
        private val logger = LoggerFactory.getLogger(StationController::class.java)
    }
}