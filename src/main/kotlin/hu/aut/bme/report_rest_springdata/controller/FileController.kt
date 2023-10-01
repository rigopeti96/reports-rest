package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.collections.Stops
import hu.aut.bme.report_rest_springdata.exception.InsertZipIntoDestinationFolderException
import hu.aut.bme.report_rest_springdata.gtfshandler.CSVParser
import hu.aut.bme.report_rest_springdata.gtfshandler.Unzipper
import hu.aut.bme.report_rest_springdata.repository.StopRepository
import hu.aut.bme.report_rest_springdata.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    /**
     * Get the new BKK GTFS file and undzip it
     * @param uploadedZip: the new ZIP archive uploaded in webapp
     * @return HTTPStatus.OK if upload was successful
     */
    @PostMapping("/uploadArchive")
    fun uploadArchive(
        @RequestParam uploadedZip: MultipartFile
    ): ResponseEntity<String>{
        try {
            insertZipIntoTargetFolder(uploadedZip)
        } catch (e: InsertZipIntoDestinationFolderException){
            println(e.stackTrace)
            return ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }

        return try {
            Unzipper.unzip("actualGtfsData.zip")
            val stops: MutableList<Stops> = CSVParser.readLineByLineExample("stops.txt")
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
}