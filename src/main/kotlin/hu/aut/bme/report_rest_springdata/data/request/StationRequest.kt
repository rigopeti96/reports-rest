package hu.aut.bme.report_rest_springdata.data.request

import javax.validation.constraints.NotBlank

data class StationRequest(
    var latitude: @NotBlank Double,
    var longitude: @NotBlank Double,
    var distance: @NotBlank Double
)

