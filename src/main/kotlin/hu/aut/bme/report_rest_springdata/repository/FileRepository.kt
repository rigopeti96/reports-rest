package hu.aut.bme.report_rest_springdata.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.io.File

@Repository
interface FileRepository: JpaRepository<File, Long> {
}