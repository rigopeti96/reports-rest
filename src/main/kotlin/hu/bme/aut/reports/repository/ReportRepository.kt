package hu.bme.aut.reports.repository

import hu.bme.aut.reports.domain.Report
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Repository
class ReportRepository {

    @PersistenceContext
    private val em: EntityManager? = null

    @Transactional
    fun save(feedback: Report?): Report? {
        return em!!.merge(feedback)
    }

    fun findAll(): List<Report?>? {
        return em!!.createQuery("SELECT r FROM Report r", Report::class.java).resultList
    }

    fun findById(id: Long): Report {
        return em!!.find(Report::class.java, id)
    }

    @Transactional
    fun deleteById(id: Long) {
        val todo: Report = findById(id)
        em!!.remove(todo)
    }
}