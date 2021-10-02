package hu.bme.aut.reports.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Report {

    /**
     * attributes - id separated
     */

    @Id
    @GeneratedValue
    private var reportId: Long? = 0

    /**
     * attributes - other attributes
     */

    private var reportType: String = ""
    private var stationName: String = ""
    private var transportType: String = ""
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private lateinit var reportDate: Date
    private lateinit var reportDateUntil: Date
    private var reporterName: String = ""

    /**
     * getter-setter section
     */
    fun getReportId(): Long?{ return reportId }
    fun setReportId(id: Long?) { reportId = id }

    fun getStationName(): String { return stationName }
    fun getTransportType(): String { return transportType }
    fun getReportType(): String { return reportType }
    fun getLatitude(): Double { return latitude }
    fun getLongitude(): Double { return longitude }
    fun getReportDate(): Date { return reportDate }
    fun getReportDateUntil(): Date { return reportDateUntil }
    fun getReporterName(): String { return reporterName }
}