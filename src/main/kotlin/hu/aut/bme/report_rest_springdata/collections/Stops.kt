package hu.aut.bme.report_rest_springdata.collections

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document(collection = "stops")
data class Stops(
    @Id val stop_id: String,
    var stop_name: String,
    var stop_lat: Double,
    var stop_lon: Double
){
    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + stop_id.hashCode()
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val (id1) = obj as Stops
        if (stop_id != id1) return false
        return true
    }
}
