package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.reports.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * Interface for reports
 */
@Repository
interface ReportRepository : JpaRepository<Report, Long>{
    /**
     * Remove old posts when a user logged in
     * @param expireDate: reference date to remove posts
     */
    fun deleteReportsByReportDateBefore(expireDate: LocalDateTime)

    /**
     *
     */
    fun getReportByLatitudeBetweenAndLongitudeBetweenOrderById(
        latitudeMin: Double,
        latitudeMax: Double,
        longitudeMin: Double,
        longitudeMax: Double
    ) : List<Report?>
}