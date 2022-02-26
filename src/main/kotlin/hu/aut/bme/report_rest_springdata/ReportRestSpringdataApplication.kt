package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.repository.ReportRepository
import hu.aut.bme.report_rest_springdata.users.User
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
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

    override fun run(vararg args: String?) {
        /*val pageable: Pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "creationDate")
        val page: Page<Report?> = reportRepository.findAll(pageable)
        page.forEach(System.out::println)*/



        /*Optional<BlogUser> jillOpt = blogUserRepo.findById("jill@example.com");
        BlogUser jill = jillOpt.orElse(null);
        // LazyInitializationException!
        jill.getPosts().forEach(System.out::println);*/

        /**
         * Dummy felhasználó létrehozása
         */
        val user = User(null, "demo", passwordEncoder.encode("demo"), true, listOf("ROLE_USER"))
        /*user.setName("demo")
        user.setPassword((passwordEncoder as PasswordEncoder).encode("demo"))
        user.setEnabled(true)
        user.setRoles(List.of("ROLE_USER"))*/

        /**
         * Dummy admin felhasználó létrehozása
         */
        val admin = User(null, "admin", passwordEncoder.encode("admin"), true, listOf("ROLE_ADMIN"))
        /*admin.setName("admin")
        admin.setPassword(passwordEncoder.encode("admin"))
        admin.setEnabled(true)
        admin.setRoles(List.of("ROLE_ADMIN"))*/
        repository.saveAll(listOf(user, admin))
    }

}

/**
 * Indítási pont
 */
fun main(args: Array<String>) {
    runApplication<ReportRestSpringdataApplication>(*args)
}
