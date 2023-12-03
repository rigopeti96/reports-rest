package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.collections.Station
import hu.aut.bme.report_rest_springdata.collections.Stops
import hu.aut.bme.report_rest_springdata.exception.InsertZipIntoDestinationFolderException
import hu.aut.bme.report_rest_springdata.gtfshandler.CSVParser
import hu.aut.bme.report_rest_springdata.gtfshandler.Unzipper
import hu.aut.bme.report_rest_springdata.repository.StationRepository
import hu.aut.bme.report_rest_springdata.repository.StopRepository
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.lang.Exception

/**
 * File uploader's endpoint class
 */
@RestController
@RequestMapping("/api/gtfshandler")
class FileController {
    @Autowired
    private lateinit var stopRepository: StopRepository

    @Autowired
    private lateinit var stationRepository: StationRepository
    /**
     * Get the new BKK GTFS file and undzip it
     * @param uploadedZip: the new ZIP archive uploaded in webapp
     * @return HTTPStatus.OK if upload was successful
     */
    @PostMapping("/uploadArchive")
    @PreAuthorize("hasRole('WEBUSER')")
    fun uploadArchive(
        @RequestParam uploadedZip: MultipartFile
    ): ResponseEntity<String>{
        println("zip upload called")
        try {
            insertZipIntoTargetFolder(uploadedZip)
        } catch (e: InsertZipIntoDestinationFolderException){
            println(e.stackTrace)
            return ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }

        return try {
            Unzipper.unzip("actualGtfsData.zip")
            stopRepository.deleteAll()
            val stationList = stationRepository.findAll()
            val stops: MutableList<Stops> = CSVParser.readLineByLineExample("stops.txt")
            for(i in 0 until stops.size){
                val actStop = stops[i]
                actStop.type = findBaseStationType(stationList, actStop.stop_lat, actStop.stop_lon)
            }
            saveStops(stops)
            ResponseEntity("Upload was successful", HttpStatus.OK)
        } catch (e: IOException){
            println(e.message)
            e.printStackTrace()
            ResponseEntity(
                "Upload was not successful due IOException",
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }

    private fun saveStops(stops: MutableList<Stops>) {
        try {
            for(i in 0 until stops.size){
                stopRepository.save(stops[i])
            }
        } catch (e: Exception){
            println(e)
        }
    }

    private fun findBaseStationType(stationList: List<Station?>, latitude: Double, longitude: Double): String{
        for(i in stationList.indices){
            if(stationList[i]!!.latitude == latitude && stationList[i]!!.longitude == longitude){
                return stationList[i]!!.stopColorType
            }
        }

        return ""
    }

    /**
     * Try to insert zip file into the destination folder
     * @throws InsertZipIntoDestinationFolderException if the insert was not successful, throws this custom exception
     */
    fun insertZipIntoTargetFolder(uploadedZip: MultipartFile){
        try {
            val encoded = uploadedZip.bytes
            File("src/main/resources/zipContainer/actualGtfsData.zip").writeBytes(encoded)
        } catch (e: Exception){
            throw InsertZipIntoDestinationFolderException("Insert Zip into destination folder was unsuccessful!", e)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FileController::class.java)
    }
}