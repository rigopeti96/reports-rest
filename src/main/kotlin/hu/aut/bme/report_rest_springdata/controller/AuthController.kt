package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.collections.Role
import hu.aut.bme.report_rest_springdata.collections.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import hu.aut.bme.report_rest_springdata.repository.RoleRepository
import org.springframework.security.crypto.password.PasswordEncoder
import hu.aut.bme.report_rest_springdata.jwt.JwtUtils
import javax.validation.Valid
import hu.aut.bme.report_rest_springdata.data.request.LoginRequest
import hu.aut.bme.report_rest_springdata.data.request.SignupRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.GrantedAuthority
import java.util.stream.Collectors
import hu.aut.bme.report_rest_springdata.data.request.response.JwtResponse
import hu.aut.bme.report_rest_springdata.data.request.response.MessageResponse
import hu.aut.bme.report_rest_springdata.users.*
import org.springframework.web.bind.annotation.*
import java.util.function.Consumer
import java.util.function.Supplier

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var employeeRepository: UserRepository

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @Autowired
    private lateinit var encoder: PasswordEncoder

    @Autowired
    private lateinit var jwtUtils: JwtUtils

    @GetMapping("/hello")
    fun hello(): String{
        println("Hello called")
        return "hello"
    }

    @PostMapping("/signin")
    fun authenticateEmployee(@RequestBody loginRequest: @Valid LoginRequest?):
            ResponseEntity<*> {

        println("login request data ${loginRequest!!.username}, ${loginRequest.password}")

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken
                (loginRequest!!.username, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils.generateJwtToken(authentication)
        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities.stream().
        map { item: GrantedAuthority -> item.authority }
            .collect(Collectors.toList())
        val jwtResponse = JwtResponse(
            jwt,
            userDetails.id!!,
            userDetails.username,
            userDetails.email!!,
            userDetails.isEnabled,
            roles
        )

        println("JWT Response data: ${jwtResponse.accessToken}, ${jwtResponse.email}")

        return ResponseEntity.ok(
            jwtResponse
        )
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest:
                     @Valid SignupRequest?): ResponseEntity<*> {

        println("signup request content: ${signUpRequest!!.email}, ${signUpRequest.username}, ${signUpRequest.name}")

        if (employeeRepository.existsByUsername(signUpRequest!!.username)!!) {
            return ResponseEntity.badRequest().
            body(MessageResponse("Error: Employeename is already taken!"))
        }
        if (employeeRepository.existsByEmail(signUpRequest.email)!!) {
            return ResponseEntity.badRequest().
            body(MessageResponse
                ("Error: Email is already in use!"))
        }

        // Create new user account
        val user = User(
            signUpRequest.username,
            signUpRequest.email,
            encoder.encode(signUpRequest.password),
            true
        )

        val strRoles = signUpRequest.roles
        val roles: MutableSet<Role> = HashSet()
        strRoles.forEach(Consumer { role: String? ->
            when (role) {
                "admin" -> {
                    val adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        ?.orElseThrow(Supplier{
                            RuntimeException("Error: Role is not found.")
                        })!!
                    roles.add(adminRole)
                }
                else -> {
                    val defaultRole = roleRepository.findByName(ERole.ROLE_USER)
                        ?.orElseThrow(Supplier
                        { RuntimeException("Error: Role is not found.") })!!
                    roles.add(defaultRole)
                }
            }
        })
        user.roles = roles
        employeeRepository.save(user)
        return ResponseEntity.
        ok(MessageResponse("User registered successfully!"))
    }

}