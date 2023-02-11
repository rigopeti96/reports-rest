package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.reports.Report
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * Interface for reports
 */
@Repository
interface ReportRepository : MongoRepository<Report?, String?> {
}