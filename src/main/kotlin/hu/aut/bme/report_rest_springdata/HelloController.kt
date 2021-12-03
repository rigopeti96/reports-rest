package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.data.HelloResponse
import hu.aut.bme.report_rest_springdata.data.ReportRepository
import hu.aut.bme.report_rest_springdata.users.User
import hu.aut.bme.report_rest_springdata.users.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.*

@RestController
@RequestMapping("/api/user")
class HelloController {
    @Autowired
    private lateinit var userRepository: UserRepository

    // User data
    @GetMapping("/me")
    fun userData(principal: Principal?): ResponseEntity<Principal> {
        return ResponseEntity(principal, HttpStatus.OK)
    }

    // Can be called by anyone
    @GetMapping("/hello")
    fun hello(): HelloResponse {
        return HelloResponse("hello")
    }

    // Can be called by authenticated user
    @PostMapping("/auth_hello")
    fun authHello(principal: Principal): Optional<User?> {
        println(principal.name.toString())
        return userRepository.findById(principal.name)
    }

    // Can be called by admins only
    @GetMapping("/admin_hello")
    @Secured("ROLE_ADMIN")
    fun adminHello(): String {
        return "Wow, you are an admin"
    }
}