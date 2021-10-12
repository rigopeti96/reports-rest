package hu.bme.aut.reports.domain

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Report(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        var reportType: String,
        var stationName: String,
        var transportType: String,
        var latitude: Double,
        var longitude: Double,
        var reportDate: Date,
        var reportDateUntil: Date,
        var reporterName: String
)