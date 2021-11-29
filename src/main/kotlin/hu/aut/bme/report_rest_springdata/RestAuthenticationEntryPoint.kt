package hu.aut.bme.report_rest_springdata

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RestAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {
    private val objectMapper = Jackson2ObjectMapperBuilder.json().build<ObjectMapper>()

    @Throws(IOException::class, ServletException::class)
    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authEx: AuthenticationException) {
        val authHeader = String.format("Auth realm=\"%s\"", realmName)
        response.addHeader("WWW-Authenticate", authHeader)
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val json = objectMapper.writeValueAsString("{\"error\":\"unauthorized\"}")
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.writer.write(json)
    }

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        realmName = "hu.bme.aut"
        super.afterPropertiesSet()
    }
}