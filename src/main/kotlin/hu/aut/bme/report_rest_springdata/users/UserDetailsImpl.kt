package hu.aut.bme.report_rest_springdata.users

import com.fasterxml.jackson.annotation.JsonIgnore
import hu.aut.bme.report_rest_springdata.collections.Role
import hu.aut.bme.report_rest_springdata.collections.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserDetailsImpl(
    val id: String?,
    private val username: String,
    val email: String?,
    @field:JsonIgnore private val password: String,
    private val isEnabled: Boolean,
    private val authorities: Collection<GrantedAuthority>
): UserDetails {
    private val serialVersionUID = 1L

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = isEnabled


    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val user = o as UserDetailsImpl
        return id == user.id
    }

    companion object {
        private const val serialVersionUID = 1L
        fun build(user: User): UserDetailsImpl {
            val authorities = user.roles.stream()
                .map { role: Role ->
                    SimpleGrantedAuthority(
                        role.name!!.name
                    )
                }.collect(Collectors.toList())
            return UserDetailsImpl(
                user.id,
                user.username!!,
                user.email,
                user.password!!,
                user.enabled,
                authorities
            )
        }
    }
}