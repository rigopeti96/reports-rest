package hu.aut.bme.report_rest_springdata.controller.reports

import hu.aut.bme.report_rest_springdata.collections.Report
import hu.aut.bme.report_rest_springdata.controller.DistanceCalculator
import hu.aut.bme.report_rest_springdata.controller.StationController
import hu.aut.bme.report_rest_springdata.data.request.StationRequest
import hu.aut.bme.report_rest_springdata.jwt.AuthEntryPointJwt
import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.station.Location
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/public/reports")
class ReportPublicController {
    @Autowired
    private lateinit var reportRepository: ReportRepository

    /**
     * Get all reports from database
     * @return list of reports
     */
    @GetMapping("/getAllReports")
    fun getAllReports(): List<Report?>{
        val responseReports = ArrayList<Report>()
        val reports = reportRepository.findAll()

        if(reports.size > 0){
            for(i in 0 until reports.size){
                if(reports[i]!!.reportDateUntil > LocalDateTime.now()){
                    responseReports.add(reports[i]!!)
                    logger.info("Report found! Report type: ${reports[i]!!.reportType}")
                }

            }
        }
        return responseReports
    }

    /**
     * Get all reports from database
     * @return list of reports
     */
    @GetMapping("/getAllReportsByDistance")
    fun getAllReportsByDistance(
        @RequestParam(name = "latitude") latitude: Double,
        @RequestParam(name = "longitude") longitude: Double,
        @RequestParam(name = "distance") distance: Double
    ): ResponseEntity<*> {
        val stationRequest = StationRequest(
            latitude,
            longitude,
            distance
        )

        val reports = reportRepository.findAll()
        val userLocation = Location(stationRequest.latitude, stationRequest.longitude)
        val responseReports = ArrayList<Report>()

        if(reports.size > 0){
            for(i in 0 until reports.size){
                val calcDist = DistanceCalculator.calculateDistance(userLocation, Location(reports[i]!!.latitude, reports[i]!!.longitude))
                if(reports[i]!!.reportDateUntil > LocalDateTime.now()){
                    if(calcDist < stationRequest.distance){
                        responseReports.add(reports[i]!!)
                        logger.info("Station found! Station name: ${reports[i]!!.reportType}, distance: $calcDist")
                    }
                }

            }
        }
        return ResponseEntity.ok().body(responseReports)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ReportPublicController::class.java)
    }
}