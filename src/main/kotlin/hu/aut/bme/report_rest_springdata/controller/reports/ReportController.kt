package hu.aut.bme.report_rest_springdata.controller.reports

import hu.aut.bme.report_rest_springdata.collections.Report
import hu.aut.bme.report_rest_springdata.data.request.ReportUpdateRequest
import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.data.request.StationRequest
import hu.aut.bme.report_rest_springdata.data.response.MessageResponse
import hu.aut.bme.report_rest_springdata.station.Location
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

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
     * Create and save report
     * @param report: Newly created report from user's client
     * @return report saved
     */
    @PostMapping("/postReport")
    @PreAuthorize("hasRole('USER')")
    fun create(@RequestBody report: Report): Report {
        report.id = UUID.randomUUID()
        reportRepository.save(report)
        return report
    }

    /**
     * Modify report
     * @param report: Report override from user's client
     * @return report saved
     */
    @PutMapping("/putReport")
    @PreAuthorize("hasRole('USER')")
    fun update(@RequestBody report: ReportUpdateRequest): ResponseEntity<*> {
        logger.info("put report called")
        if(reportRepository.findById(report.id) == null){
            return ResponseEntity.badRequest().body((MessageResponse("Error: Report with given data is not found!")))
        }

        val reportFound = reportRepository.findById(report.id)
        reportFound!!.reportDateUntil = LocalDateTime.parse(report.reportDateUntil)
        reportRepository.save(reportFound)

        return ResponseEntity.ok().body((reportRepository.findById(report.id)))
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ReportController::class.java)
    }
}