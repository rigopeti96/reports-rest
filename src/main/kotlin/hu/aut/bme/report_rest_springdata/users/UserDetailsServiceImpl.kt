package hu.aut.bme.report_rest_springdata.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    private val repository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository!!.findById(username)
        return if (!user.isPresent) throw UsernameNotFoundException("$username is an invalid username") else UserDetailsImpl(
            user.get()
        )
    }
}