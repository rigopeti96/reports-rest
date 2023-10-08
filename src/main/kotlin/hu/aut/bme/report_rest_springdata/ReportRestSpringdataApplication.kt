package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.collections.Role
import hu.aut.bme.report_rest_springdata.collections.User
import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.repository.RoleRepository
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import hu.aut.bme.report_rest_springdata.users.ERole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class ReportRestSpringdataApplication: CommandLineRunner{
    @Autowired
    private lateinit var reportRepository: ReportRepository

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var roleRepository: RoleRepository

    @Throws(Exception::class)
    override fun run(vararg args: String) {
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ReportRestSpringdataApplication::class.java, *args)
        }
    }
}
