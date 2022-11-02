package hu.aut.bme.report_rest_springdata.repository

import hu.aut.bme.report_rest_springdata.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

/**
 * Interface for users
 */
/*
Last version

@Repository
interface UserRepository : JpaRepository<User, String>*/
@Repository
interface UserRepository : MongoRepository<User?, String?> {
    fun findByUsername(username: String?): Optional<User?>?
    fun existsByUsername(username: String?): Boolean?
    fun existsByEmail(email: String?): Boolean?
}