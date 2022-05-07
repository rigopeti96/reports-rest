package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.users.User
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * User's endpoint class
 */
@RestController
@RequestMapping("/api/user")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var reportRepository: ReportRepository

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @GetMapping("/hello")
    fun hello(): String{
        return "hello"
    }

    /**
     * Get user data
     * @param principal: user's principal data
     * @return logged user and status
     */
    @GetMapping("/me")
    fun userData(principal: Principal?): ResponseEntity<Principal> = ResponseEntity(principal, HttpStatus.OK)

    /**
     * Logging in + remove posts before today 0:00
     * @param principal: user's principal data
     * @return found user's principal
     */
    @PostMapping("/login")
    fun login(principal: Principal): Optional<User?> {
        val today = LocalDateTime.now()
        val expireDate = LocalDateTime.of(today.year, today.month, today.dayOfMonth, 0, 0)
        reportRepository.deleteReportsByReportDateBefore(expireDate)
        return userRepository.findById(principal.name)
    }

    /**
     * Create new user
     * @param user: Data of new user
     * @return created user
     */
    @PostMapping("/createUser")
    fun createUser(@RequestBody user: User): User = userRepository.save(user)

    /**
     * Delete user
     * @param user: Data of deletable user
     */
    @DeleteMapping("/deleteUser")
    fun deleteUser(@RequestBody user: User): Unit = userRepository.delete(user)
}