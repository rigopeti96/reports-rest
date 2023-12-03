package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import hu.aut.bme.report_rest_springdata.data.response.MessageResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

/**
 * User's endpoint class
 */
@CrossOrigin(origins = ["*"], maxAge = 4800)
@RestController
@RequestMapping("/api/users")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var reportRepository: ReportRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    fun userAccess(): MessageResponse {
        return MessageResponse("User zone")
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun adminAccess(): MessageResponse {
        return MessageResponse("Admin zone")
    }
}