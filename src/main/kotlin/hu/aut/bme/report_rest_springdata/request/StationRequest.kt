package hu.aut.bme.report_rest_springdata.request

data class StationRequest (
    val latitude: Double,
    val longitude: Double,
    val distance: Double
)
