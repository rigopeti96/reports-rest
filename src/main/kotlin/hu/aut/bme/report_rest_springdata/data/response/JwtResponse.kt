package hu.aut.bme.report_rest_springdata.data.response

import org.bson.types.ObjectId
import java.util.*

class JwtResponse(
    var accessToken: String,
    var id: ObjectId,
    var employeename: String,
    var email: String,
    var enabled: Boolean,
    val roles: List<String>
) {
    var tokenType = "Bearer"
}