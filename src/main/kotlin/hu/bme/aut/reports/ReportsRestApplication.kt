package hu.bme.aut.reports

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReportsRestApplication{
    fun main(args: Array<String>) {
        runApplication<ReportsRestApplication>(*args)
    }
}
