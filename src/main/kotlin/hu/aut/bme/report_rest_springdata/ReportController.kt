package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.data.HelloResponse
import hu.aut.bme.report_rest_springdata.data.Report
import hu.aut.bme.report_rest_springdata.data.ReportRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

/**
 * Report végpontokat összefogó osztály
 */
@RestController
@RequestMapping("/api/reports")
class ReportController {

    @Autowired
    private lateinit var reportRepository: ReportRepository

    /**
     * Felhasználó adatainak lekérése
     */
    @GetMapping("/me")
    fun userData(principal: Principal?): ResponseEntity<Principal> {
        return ResponseEntity(principal, HttpStatus.OK)
    }

    /**
     * Bejelentő adatainak lekérése
     */
    @GetMapping("/reporterName")
    fun reporterName(principal: Principal?): String {
        return "Reporter: " + principal?.name
    }

    /**
     * Kapcsolat ellenőrzése; DEBUG funkció
     */
    @GetMapping("/hello")
    fun hello(): HelloResponse {
        return HelloResponse("hello")
    }

    /**
     * Összes bejelentés lekérése
     */
    @GetMapping("/getAllReports")
    fun getAllReports(principal: Principal?): List<Report?>?{
        return reportRepository.findPosts()
    }

    /**
     * Bejegyzés létrehozása és mentése
     */
    @PostMapping("/postReport")
    fun create(@RequestBody report: Report): Report{
        println(report.id.toString())
        return reportRepository.save(report)
    }

    /**
     * Bejegyzés módosítása
     */
    @PutMapping
    fun update(@RequestBody report: Report): Report = reportRepository.save(report)
}