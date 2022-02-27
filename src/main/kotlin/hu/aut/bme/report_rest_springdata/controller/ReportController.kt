package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.reports.Report
import hu.aut.bme.report_rest_springdata.repository.ReportRepository
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
    @PutMapping
    fun update(@RequestBody report: Report): Report = reportRepository.save(report)
}