package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.jwt.AuthEntryPointJwt
import hu.aut.bme.report_rest_springdata.station.Station
import hu.aut.bme.report_rest_springdata.repository.StopstationRepository
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import hu.aut.bme.report_rest_springdata.request.LocationRequest
import hu.aut.bme.report_rest_springdata.station.Location
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Station's endpoint class
 */
@RestController
@RequestMapping("/api/station")
class StationController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var stopStationRepository: StopstationRepository

    /**
     * Send the stations' data which are closer than stationRequest.distance attribute
     * @param stationRequest: Request from user contains position data and distance
     * @return list of station which distance's is lower than the radius
     */
    @GetMapping("/getStations")
    fun getStations(stationRequest: LocationRequest): ArrayList<Station>{
        logger.info("statinon request values: ${stationRequest.latitude}, ${stationRequest.longitude}, ${stationRequest.distance}")
        val locations = stopStationRepository.findAll()
        val responseStations = ArrayList<Station>()

        for(i in 0 until locations.size){
            val location = Location(locations[i]!!.latitude, locations[i]!!.longitude)
            if(DistanceCalculator.calcDistance(location, stationRequest) <= stationRequest.distance){
                responseStations.add(locations[i]!!)
            }
        }

        return responseStations
    }

    companion object {
        private val logger = LoggerFactory.getLogger(StationController::class.java)
    }
}