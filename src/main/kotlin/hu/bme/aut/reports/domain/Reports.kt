package hu.bme.aut.reports.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Reports {

    /**
     * attributes - id separated
     */

    @Id
    @GeneratedValue
    private var reportId: Long = 0

    /**
     * attributes - other attributes
     */

    private var reportType: String = ""
    private var stationName: String = ""
    private var transportType: String = ""
    private var Latitude: Double = 0.0
    private var Longitude: Double = 0.0
    private lateinit var reportDate: Date
    private lateinit var reportDateUntil: Date
    private var reporterName: String = ""

    /**
     * getter-setter section
     */

    fun getReportId(): Long{ return reportId }
    fun getStationName(): String { return stationName }
    fun getTransportType(): String { return transportType }
    fun getLatitude(): Double { return Latitude }
    fun getLongitude(): Double { return Longitude }
    fun getReportDate(): Date { return reportDate }
    fun getReportDateUntil(): Date { return reportDateUntil }
    fun getReporterName(): String { return reporterName }
}