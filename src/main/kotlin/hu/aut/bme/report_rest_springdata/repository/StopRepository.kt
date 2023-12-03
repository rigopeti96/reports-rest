package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.collections.Stops
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StopRepository : MongoRepository<Stops?, String?> {

}