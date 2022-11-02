package hu.aut.bme.report_rest_springdata.request

import javax.validation.constraints.NotBlank

class LoginRequest {
    lateinit var username: @NotBlank String
    lateinit var password: @NotBlank String
}