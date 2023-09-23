package hu.aut.bme.report_rest_springdata.data.request.response

import java.util.*

class JwtResponse(
    var accessToken: String,
    var id: UUID,
    var employeename: String,
    var email: String,
    var enabled: Boolean,
    val roles: List<String>
) {
    var tokenType = "Bearer"
}