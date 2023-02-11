package hu.aut.bme.report_rest_springdata.station

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Document(collection = "stations")
data class Station(
    @Id val id: String? = null,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val stopType: String
){
    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + id.hashCode()
        return result
    }
}