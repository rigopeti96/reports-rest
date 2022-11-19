package hu.aut.bme.report_rest_springdata.gtfsdata

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Trip(
    @Id val id: String,
    val routeId: Long,
    val serviceId: String,
    val tripHeadsign: String,
    val directionId: Int,
    val blockId: String,
    val shapeId: String,
    val wheelchairAccessible: Boolean,
    val bikesAllowed: Boolean,
    val boardingDoor: Int
)
