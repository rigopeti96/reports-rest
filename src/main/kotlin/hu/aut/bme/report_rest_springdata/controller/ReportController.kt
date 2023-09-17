package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.collections.Report
import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.data.request.StationRequest
import hu.aut.bme.report_rest_springdata.station.Location
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.security.Principal

/**
 * Report endpoint class
 */
@RestController
@RequestMapping("/api/reports")
class ReportController {

    @Autowired
    private lateinit var reportRepository: ReportRepository

    /**
     * Get reporter name
     * @param principal: user for search
     * @return reporter's name
     */
    @GetMapping("/reporterName")
    fun reporterName(principal: Principal?): String {
        return "Reporter: " + principal?.name
    }

    /**
     * Get all reports from database
     * @param principal: reporter user data
     * @return list of reports
     */
    @GetMapping("/getAllReports")
    fun getAllReports(principal: Principal?): List<Report?>{
        return reportRepository.findAll()
    }

    /**
     * Get all reports from database
     * @param principal: reporter user data
     * @return list of reports
     */
    @GetMapping("/getAllReportsByDistance")
    fun getAllReportsByDistance(principal: Principal?, reportRequest: StationRequest): List<Report?>{
        val reports = reportRepository.findAll()
        val responseReports = ArrayList<Report>()

        for(i in 0 until reports.size){
            val location = Location(reports[i]!!.latitude, reports[i]!!.longitude)
            if(DistanceCalculator.calcDistance(location, reportRequest) <= reportRequest.distance){
                responseReports.add(reports[i]!!)
            }
        }
        return responseReports
    }

    /**
     * Create and save report
     * @param report: Newly created report from user's client
     * @return report saved
     */
    @PostMapping("/postReport")
    fun create(@RequestBody report: Report): Report = reportRepository.save(report)

    /**
     * Modify report
     * @param report: Report override from user's client
     * @return report saved
     */
    @PutMapping("/putReport")
    fun update(@RequestBody report: Report): Report = reportRepository.save(report)
}