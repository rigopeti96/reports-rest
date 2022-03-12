package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

/**
 * Interface for users
 */
@Repository
interface UserRepository : JpaRepository<User, String>