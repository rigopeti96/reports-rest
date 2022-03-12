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
open class ReportRestSpringdataApplication: CommandLineRunner{
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

        val user = User(null, "demo", "demoMan" ,passwordEncoder.encode("demo"), true, listOf("ROLE_USER"))
        /*user.setName("demo")
        user.setPassword((passwordEncoder as PasswordEncoder).encode("demo"))
        user.setEnabled(true)
        user.setRoles(List.of("ROLE_USER"))*/
        repository.saveAll(listOf(user))
    }

}

/**
 * Entry point
 */
fun main(args: Array<String>) {
    runApplication<ReportRestSpringdataApplication>(*args)
}
