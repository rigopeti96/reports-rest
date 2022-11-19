package hu.aut.bme.report_rest_springdata.gtfshandler

import hu.aut.bme.report_rest_springdata.gtfsdata.Pathways
import java.io.FileInputStream
import java.io.InputStream

object CSVParser {
    fun readPathwayCsv(inputStream: InputStream): List<Pathways>{
        val reader = inputStream.bufferedReader()
        val header = reader.readLine()

        return reader.lineSequence()
            .filter {
                it.isNotBlank()
            }
            .map {
                val (
                    id,
                    pathway_mode,
                    sourceId,
                    destinationId,
                    travelTime) = it.split(",", ignoreCase = false, limit = 0)
                Pathways(id.trim(), sourceId.trim(), destinationId.trim(), travelTime.trim().toInt())
            }.toList()
    }
}