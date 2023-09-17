package hu.aut.bme.report_rest_springdata.data.request

data class StationRequest (
    val latitude: Double,
    val longitude: Double,
    val distance: Double
)
