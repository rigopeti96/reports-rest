package hu.aut.bme.report_rest_springdata.collections

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.*

/**
 * Report representation class
 */
@Document(collection = "reports")
data class Report(
    @Id val id: String? = null,
    val reportType: String,
    val stationName: String,
    val transportType: String,
    val latitude: Double,
    val longitude: Double,
    val reportDate: LocalDateTime,
    var reportDateUntil: LocalDateTime,
    val reporterName: String,
    var modifierName: String
) {
    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + id.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val (id1) = obj as Report
        if (id != id1) return false
        return true
    }

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , reportType = $reportType , stationName = $stationName , transportType = $transportType , latitude = $latitude , longitude = $longitude , reporterName = $reporterName )"
    }
}