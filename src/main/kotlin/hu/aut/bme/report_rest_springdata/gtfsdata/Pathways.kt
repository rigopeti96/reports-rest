package hu.aut.bme.report_rest_springdata.gtfsdata

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Pathways(
    @Id val id: String,
    val sourceId: String,
    val destinationId: String,
    val travelTime: Int
)