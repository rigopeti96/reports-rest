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
    private val reportRepository: ReportRepository? = null

    @GetMapping
    fun getAll(): List<Report?>? {
        return reportRepository?.findAll()
    }

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Report?>? {
        val report: Report = reportRepository?.findById(id)!!
        return ResponseEntity.ok(report)
    }

    @PostMapping
    fun create(@RequestBody Report: Report): Report? {
        Report.setReportId(null)
        return reportRepository?.save(Report)
    }

    @PutMapping
    fun update(@RequestBody Report: Report): ResponseEntity<Report?>? {
        var t: Report = Report.getReportId()?.let { reportRepository!!.findById(it) } ?: return ResponseEntity.notFound().build()
        t = reportRepository?.save(Report)!!
        return ResponseEntity.ok(t)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<*>? {
        val report: Report = reportRepository?.findById(id)!!
        return run {
            reportRepository?.deleteById(id)
            ResponseEntity.ok().build<Any>()
        }
    }
}