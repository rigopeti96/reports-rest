package hu.bme.aut.reports.repository

import hu.bme.aut.reports.domain.Report
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Repository
open class ReportRepository {
    @PersistenceContext
    private val em: Any = Object()
    @Transactional
    open fun save(feedback: Report): Report {
        return (em as EntityManager).merge(feedback)
    }

    @Transactional
    open fun findAll(): List<Report> {
        return (em as EntityManager).createQuery("SELECT r FROM Report r", Report::class.java).resultList
    }

    @Transactional
    open fun findById(id: Long): Report {
        return (em as EntityManager).find(Report::class.java, id)
    }

    @Transactional
    open fun deleteById(id: Long) {
        val report: Report = findById(id)
        (em as EntityManager).remove(report)
    }
}