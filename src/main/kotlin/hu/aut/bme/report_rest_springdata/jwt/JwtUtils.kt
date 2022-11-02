package hu.aut.bme.report_rest_springdata.jwt

import java.lang.IllegalArgumentException
import hu.aut.bme.report_rest_springdata.users.UserDetailsImpl
import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils {
    @Value("\${aut.app.jwtExpirationMs}")
    private val jwtExpirationMs = 0

    @Value("\${aut.app.jwtSecret}")
    private val jwtSecret: String? = null
    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

    fun generateJwtToken(authentication: Authentication): String {
        val employeePrincipal = authentication.principal as UserDetailsImpl
        return Jwts.builder().setSubject(employeePrincipal.username).setIssuedAt(Date())
            .setExpiration(
                Date(
                    Date().time +
                            jwtExpirationMs
                )
            )
            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    fun getUserNameFromJwtToken(token: String?): String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.subject
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtUtils::class.java)
    }
}