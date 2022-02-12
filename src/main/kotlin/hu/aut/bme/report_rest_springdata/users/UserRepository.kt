package hu.aut.bme.report_rest_springdata.users

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Felhasználók lekérdezéseit generáló interface
 */
@Repository
interface UserRepository : JpaRepository<User?, String?>