package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.reports.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * A bejegyzések lekérdezéért felelős interface
 */
@Repository
interface ReportRepository : JpaRepository<Report, Long>