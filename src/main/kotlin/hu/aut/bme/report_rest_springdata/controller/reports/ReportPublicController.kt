package hu.aut.bme.report_rest_springdata.controller.reports

import hu.aut.bme.report_rest_springdata.collections.Report
import hu.aut.bme.report_rest_springdata.controller.DistanceCalculator
import hu.aut.bme.report_rest_springdata.data.request.StationRequest
import hu.aut.bme.report_rest_springdata.jwt.AuthEntryPointJwt
import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.station.Location
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
        logger.info("Get all reports called")
        return reportRepository.findAll()
    }

    /**
     * Get all reports from database
     * @return list of reports
     */
    @GetMapping("/getAllReportsByDistance")
    fun getAllReportsByDistance(reportRequest: StationRequest): List<Report?>{
        val reports = reportRepository.findAll()
        val responseReports = ArrayList<Report>()
        println("Reports size: ${reports.size}")

        for(i in 0 until reports.size){
            val location = Location(reports[i]!!.latitude, reports[i]!!.longitude)
            println("Distance between :${DistanceCalculator.calcDistance(location, reportRequest)}")
            if(DistanceCalculator.calcDistance(location, reportRequest) <= reportRequest.distance){
                responseReports.add(reports[i]!!)
            }
        }
        return responseReports
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ReportPublicController::class.java)
    }
}