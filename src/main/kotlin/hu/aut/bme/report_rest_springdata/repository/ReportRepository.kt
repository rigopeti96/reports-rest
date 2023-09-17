package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.collections.Report
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Interface for reports
 */
@Repository
interface ReportRepository : MongoRepository<Report?, String?> {
}