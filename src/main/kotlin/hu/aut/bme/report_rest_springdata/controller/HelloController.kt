package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.reports.HelloResponse
import hu.aut.bme.report_rest_springdata.users.User
import hu.aut.bme.report_rest_springdata.repository.UserRepository
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

/**
 * A felhasználói végpontokat összefogó osztály
 */
@RestController
@RequestMapping("/api/user")
class HelloController {
    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * Felhasználói adatok lekérése
     */
    @GetMapping("/me")
    fun userData(principal: Principal?): ResponseEntity<Principal> {
        return ResponseEntity(principal, HttpStatus.OK)
    }

    /**
     * Kapcsolat ellenőrzése; DEBUG funkció
     */
    @GetMapping("/hello")
    fun hello(): HelloResponse {
        return HelloResponse("hello")
    }

    /**
     * A bejelentkezett felhasználók által hívható kapcsolat ellenőrzése; DEBUG funkció
     */
    @PostMapping("/auth_hello")
    fun authHello(principal: Principal): Optional<User?> {
        println(principal.name.toString())
        return userRepository.findById(principal.name)
    }

    /**
     * A bejelentkezett admin felhasználók által hívható kapcsolat ellenőrzése; DEBUG funkció
     */
    @GetMapping("/admin_hello")
    @Secured("ROLE_ADMIN")
    fun adminHello(): String {
        return "Wow, you are an admin"
    }
}