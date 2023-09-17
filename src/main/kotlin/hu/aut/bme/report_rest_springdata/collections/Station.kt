package hu.aut.bme.report_rest_springdata.collections

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.Id

@Document(collection = "stations")
data class Station(
    @Id val id: UUID? = null,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val stopType: String,
    val stopColorType: String
){
    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + id.hashCode()
        return result
    }
}