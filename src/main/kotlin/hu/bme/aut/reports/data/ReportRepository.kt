package hu.bme.aut.reports.data

import hu.bme.aut.reports.domain.Report
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ReportRepository : JpaRepository<Report?, Int?> {
    //public List<Report> findAllByContentContains(String str);
    @Query("SELECT bp FROM Report bp")
    fun findPostsWhoseTitleStartsWithM(): List<Report?>?
}