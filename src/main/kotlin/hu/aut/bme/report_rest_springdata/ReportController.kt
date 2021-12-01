package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.data.HelloResponse
import hu.aut.bme.report_rest_springdata.data.Report
import hu.aut.bme.report_rest_springdata.data.ReportRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/reports")
class ReportController {

    @Autowired
    private lateinit var reportRepository: ReportRepository

    // User data
    @GetMapping("/me")
    fun userData(principal: Principal?): ResponseEntity<Principal> {
        return ResponseEntity(principal, HttpStatus.OK)
    }

    // User data
    @GetMapping("/reporterName")
    fun reporterName(principal: Principal?): String {
        return "Reporter: " + principal?.name
    }

    @GetMapping("/hello")
    fun hello(): HelloResponse {
        return HelloResponse("hello")
    }

    @GetMapping("/getAllReports")
    fun getAllReports(principal: Principal?): List<Report?>?{
        return reportRepository.findPosts()
    }

    @PostMapping("/postReport")
    fun create(@RequestBody report: Report): Report{
        println(report.id.toString())
        return reportRepository.save(report)
    }

    @PutMapping
    fun update(@RequestBody report: Report): Report = reportRepository.save(report)
}