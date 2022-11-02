package hu.aut.bme.report_rest_springdata.response

class JwtResponse(
    var accessToken: String,
    var id: String,
    var employeename: String,
    var email: String,
    var enabled: Boolean,
    val roles: List<String>
) {
    var tokenType = "Bearer"
}