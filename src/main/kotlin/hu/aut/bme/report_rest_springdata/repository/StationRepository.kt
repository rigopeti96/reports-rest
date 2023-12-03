package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.collections.Station
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StationRepository: MongoRepository<Station?, String?> { }