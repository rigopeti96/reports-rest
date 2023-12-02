package hu.aut.bme.report_rest_springdata

import hu.aut.bme.report_rest_springdata.gtfshandler.CSVParser
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CSVParserTest {

    @Test
    fun readCSV(){
        val read = CSVParser.readLineByLineExample("stops.txt")
        for (i in read.indices){
            println(read[i])
        }
    }
}