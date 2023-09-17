package hu.aut.bme.report_rest_springdata.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SignupRequest {
    lateinit var name: @NotBlank @Size(min = 3, max = 20) String
    lateinit var username: @NotBlank @Size(min = 3, max = 20) String
    lateinit var password: @NotBlank @Size(min = 6, max = 40) String
    lateinit var email: @NotBlank @Size(max = 50) @Email String
    lateinit var roles: Set<String>
        private set
    fun setRole(roles: Set<String>) {
        this.roles = roles
    }
}