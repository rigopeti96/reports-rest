package hu.aut.bme.report_rest_springdata.repository

import org.springframework.data.mongodb.repository.MongoRepository
import hu.aut.bme.report_rest_springdata.users.ERole
import hu.aut.bme.report_rest_springdata.users.Role
import java.util.*

interface RoleRepository : MongoRepository<Role?, String?> {
    fun findByName(name: ERole?): Optional<Role?>?
}