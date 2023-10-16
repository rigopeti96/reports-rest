package hu.aut.bme.report_rest_springdata.jwt

import org.springframework.security.web.AuthenticationEntryPoint
import kotlin.Throws
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils

@Component
class AuthEntryPointJwt : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest, response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val jwt = parseJwt(request)
        logger.debug("jwt value in AuthTokenFilter: $jwt")
        logger.error("Unauthorized error: {}", authException.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized")
    }

    private fun parseJwt(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader("Authorization")
        return if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
            headerAuth.substring(7, headerAuth.length)
        } else null
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AuthEntryPointJwt::class.java)
    }
}


