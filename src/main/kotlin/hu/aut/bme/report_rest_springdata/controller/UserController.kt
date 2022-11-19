package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.users.User
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import hu.aut.bme.report_rest_springdata.response.MessageResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

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

    @GetMapping("/hello")
    fun hello(): String{
        return "hello"
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') ")
    fun userAccess(): MessageResponse {
        return MessageResponse("User zone")
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun adminAccess(): MessageResponse {
        return MessageResponse("Admin zone")
    }
}