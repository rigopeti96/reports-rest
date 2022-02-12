package hu.aut.bme.report_rest_springdata.data

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Bejegyzéseket reprezentáló adatosztály
 */
@Entity
data class Report(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
    var reportType: String,
    var stationName: String,
    var transportType: String,
    var latitude: Double,
    var longitude: Double,
    //var reportDate: LocalDateTime,
    //var reportDateUntil: LocalDateTime,
    var reporterName: String
) {
    constructor() : this(null, "", "", "", 0.0, 0.0, /*LocalDateTime.now(), LocalDateTime.now(),*/ "")

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
}