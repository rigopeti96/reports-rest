package hu.bme.aut.reports.controller

import hu.bme.aut.reports.domain.Report
import hu.bme.aut.reports.repository.ReportRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reports")
class ReportController {

    @Autowired
    private lateinit var reportRepository: ReportRepository

    @GetMapping
    fun getAll(): List<Report?>? = reportRepository.findAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): Report = reportRepository.findById(id)

    @PostMapping
    fun create(@RequestBody Report: Report): Report? {
        Report.Id = null
        return reportRepository.save(Report)
    }

    @PutMapping
    fun update(@RequestBody Report: Report): Report? = reportRepository.save(Report)!!

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<*>? {
        val report: Report = reportRepository.findById(id)
        return if (report == null) ResponseEntity.notFound().build<Any>() else {
            reportRepository.deleteById(id)
            ResponseEntity.ok().build<Any>()
        }
    }
}