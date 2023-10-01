package hu.aut.bme.report_rest_springdata.gtfshandler

import com.opencsv.CSVReader
import hu.aut.bme.report_rest_springdata.collections.Stops
import hu.aut.bme.report_rest_springdata.repository.StopRepository
import org.springframework.beans.factory.annotation.Autowired
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList
import kotlin.io.path.exists


object CSVParser {

    @Throws(Exception::class)
    fun readLineByLine(filePath: Path?): MutableList<Stops> {
        val stopList: MutableList<Stops> = ArrayList()
        var firstRow = true
        try {
            val reader = Files.newBufferedReader(filePath)
            val csvReader = CSVReader(reader)
            var line: Array<String>
            while ((csvReader.readNext().also { line = it }) != null) {
                if(!firstRow){
                    if(filePath!!.fileName.toString() == "stops.txt"){
                        stopList.add(createStopValue(line))
                    }
                } else{
                    firstRow = false
                }
            }
        } catch (e: Exception){
            println(e)
        }
        println("readLineByLine stopList size: ${stopList.size}")

        return stopList
    }

    private fun createStopValue(line: Array<String>): Stops {
        val id = line[0]
        val name = line[1]
        val lat = line[2].toDouble()
        val lon = line[3].toDouble()

        return Stops(
            id,
            name,
            lat,
            lon
        )
    }

    @Throws(Exception::class)
    fun readLineByLineExample(fileName: String) : MutableList<Stops> {
        val fileZip = "src/main/resources/unzipStage/$fileName"
        val path = Paths.get(fileZip)
        if(!path.exists()){
            throw IOException("$fileZip does not exists")
        }
        return readLineByLine(path)
    }
}
