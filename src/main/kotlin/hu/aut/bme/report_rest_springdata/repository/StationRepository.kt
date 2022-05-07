package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.station.Station
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StationRepository: JpaRepository<Station, Long> {

}