package hu.bme.aut.reports

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component

@SpringBootApplication
@Component
class ReportsRestApplication{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ReportsRestApplication::class.java, *args)
        }
    }
}
