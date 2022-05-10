package hu.aut.bme.report_rest_springdata.request

data class LocationRequest (
    val latitude: Double,
    val longitude: Double,
    val distance: Double
)
