package hu.bme.aut.reports.repository

import hu.bme.aut.reports.domain.Report
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Repository
open class ReportRepository {

    @PersistenceContext
    private lateinit var em: EntityManager

    @Transactional
    open fun save(feedback: Report): Report = em.merge(feedback)

    fun findAll(): List<Report> = em.createQuery("SELECT r FROM Report r", Report::class.java).resultList

    fun findById(id: Long): Report = em.find(Report::class.java, id)

    @Transactional
    open fun deleteById(id: Long) {
        val todo: Report = findById(id)
        em.remove(todo)
    }
}