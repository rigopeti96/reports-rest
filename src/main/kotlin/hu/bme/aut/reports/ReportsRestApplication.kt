package hu.bme.aut.reports

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class ReportsRestApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReportsRestApplication::class.java, *args)
}


