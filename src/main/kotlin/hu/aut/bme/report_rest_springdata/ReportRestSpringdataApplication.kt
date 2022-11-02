package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.repository.RoleRepository
import hu.aut.bme.report_rest_springdata.users.User
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import hu.aut.bme.report_rest_springdata.users.ERole
import hu.aut.bme.report_rest_springdata.users.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
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
        /*try {
            val role = Role()
            role.name= ERole.ROLE_USER
            roleRepository.save(role)
            val role2 = Role()
            role2.name=ERole.ROLE_ADMIN
            roleRepository.save(role2)
        } catch (e: Exception) {

        }*/
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ReportRestSpringdataApplication::class.java, *args)
        }
    }
}
