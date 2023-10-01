package hu.aut.bme.report_rest_springdata.gtfshandler

import com.opencsv.CSVReader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import kotlin.io.path.exists


object CSVParser {
    @Throws(Exception::class)
    fun readLineByLine(filePath: Path?): List<Array<String>> {
        val list: MutableList<Array<String>> = ArrayList()

        try {
            val reader = Files.newBufferedReader(filePath)
            val csvReader = CSVReader(reader)
            var line: Array<String>
            while ((csvReader.readNext().also { line = it }) != null) {
                when(filePath!!.fileName.toString()){

                }
                list.add(line)
            }
        } catch (e: Exception){
            println(e)
        }
        return list
    }

    @Throws(Exception::class)
    fun readLineByLineExample(fileName: String) : List<Array<String>> {
        val fileZip = "src/main/resources/unzipStage/$fileName"
        val path = Paths.get(fileZip)
        if(!path.exists()){
            throw IOException("$fileZip does not exists")
        }
        return readLineByLine(path)
    }
}
