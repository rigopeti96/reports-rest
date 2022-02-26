package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.reports.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * A bejegyzések lekérdezéért felelős interface
 */
@Repository
interface ReportRepository : JpaRepository<Report, Long> {
    //public List<Report> findAllByContentContains(String str);

    /**
     * Az összes bejegyzés lekérése
     */
    @Query("SELECT bp FROM Report bp")
    fun findPosts(): List<Report>?
}