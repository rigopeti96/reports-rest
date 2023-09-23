package hu.aut.bme.report_rest_springdata.data.request

import javax.validation.constraints.NotBlank

class StationRequest {
    var latitude: @NotBlank Double = 0.0
    var longitude: @NotBlank Double = 0.0
    var distance: @NotBlank Double = 0.0
}
