package hu.aut.bme.report_rest_springdata.controller

import hu.aut.bme.report_rest_springdata.exception.InsertZipIntoDestinationFolderException
import hu.aut.bme.report_rest_springdata.gtfshandler.Unzipper
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
            Unzipper.unzip("src/main/resources/zipContainer/")
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

    /**
     * Try to insert zip file into the destination folder
     * @throws InsertZipIntoDestinationFolderException if the insert was not successful, throws this custom exception
     */
    fun insertZipIntoTargetFolder(uploadedZip: MultipartFile){
        try {
            val encoded = uploadedZip.bytes
            File("src/main/resources/zipContainer/${uploadedZip.name}.zip").writeBytes(encoded)
        } catch (e: Exception){
            throw InsertZipIntoDestinationFolderException("Insert Zip into destination folder was unsuccessful!", e)
        }
    }
}