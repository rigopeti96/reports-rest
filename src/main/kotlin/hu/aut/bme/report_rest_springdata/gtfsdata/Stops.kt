package hu.aut.bme.report_rest_springdata.gtfsdata

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Stops(
    @Id val id: String,
    val stopName: String,
    val stopType: String,
    val stopColorType: String,
    val stopLat: Double,
    val stopLon: Double
)
