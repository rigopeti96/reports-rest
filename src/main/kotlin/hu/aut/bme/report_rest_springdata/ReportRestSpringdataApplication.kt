package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.data.ReportRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReportRestSpringdataApplication: CommandLineRunner{
    @Autowired
    private lateinit var reportRepository: ReportRepository

    @Throws(Exception::class)
    override fun run(vararg args: String?) {
        /*val pageable: Pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "creationDate")
        val page: Page<Report?> = reportRepository.findAll(pageable)
        page.forEach(System.out::println)*/



        /*Optional<BlogUser> jillOpt = blogUserRepo.findById("jill@example.com");
        BlogUser jill = jillOpt.orElse(null);
        // LazyInitializationException!
        jill.getPosts().forEach(System.out::println);*/
    }

}

fun main(args: Array<String>) {
    runApplication<ReportRestSpringdataApplication>(*args)
}
