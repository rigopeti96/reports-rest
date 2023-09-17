package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.gtfshandler.Unzipper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.IOException
import kotlin.io.path.Path

/**
 * File uploader's endpoint class
 */
@RestController
@RequestMapping("/api/gtfshandler")
class FileController {

    /**
     * Get the new BKK GTFS file and undzip it
     * @param uploadedZip: the new ZIP archive uploaded in webapp
     * @return HTTPStatus.OK if upload was successful
     */
    @PostMapping("/uploadArchive")
    fun uploadArchive(uploadedZip: File): ResponseEntity<String>{
        return try {
            Unzipper.unzip("src/main/resources/zipContainer")
            ResponseEntity("Upload was successful", HttpStatus.OK)
        } catch (e: IOException){
            ResponseEntity(
                "Upload was not successful due IOException",
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }

    }
}