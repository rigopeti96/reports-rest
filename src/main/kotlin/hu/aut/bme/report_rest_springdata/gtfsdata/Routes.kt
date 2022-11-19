package hu.aut.bme.report_rest_springdata.gtfsdata

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Routes(
    @Id val id: Long,
    val shortName: String,
    val routeDesc: String,
    val routeColor: String
)
