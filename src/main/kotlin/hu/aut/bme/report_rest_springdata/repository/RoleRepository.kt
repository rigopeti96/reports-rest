package hu.aut.bme.report_rest_springdata.repository

import org.springframework.data.mongodb.repository.MongoRepository
import hu.aut.bme.report_rest_springdata.users.ERole
import hu.aut.bme.report_rest_springdata.collections.Role
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : MongoRepository<Role?, String?> {
    fun findByName(name: ERole?): Optional<Role?>?
}