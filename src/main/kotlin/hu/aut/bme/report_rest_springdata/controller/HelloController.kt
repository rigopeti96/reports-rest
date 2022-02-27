package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.users.User
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.*

/**
 * User's endpoint class
 */
@RestController
@RequestMapping("/api/user")
class HelloController {
    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * Get user data
     * @param principal: user's principal data
     * @return logged user and status
     */
    @GetMapping("/me")
    fun userData(principal: Principal?): ResponseEntity<Principal> {
        return ResponseEntity(principal, HttpStatus.OK)
    }

    /**
     * Logging in
     * @param principal: user's principal data
     * @return found user's principal
     */
    @PostMapping("/auth_hello")
    fun authHello(principal: Principal): Optional<User?> {
        return userRepository.findById(principal.name)
    }
}