package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.station.Station
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StopstationRepository: MongoRepository<Station?, String?> {

}