package hu.aut.bme.report_rest_springdata.users

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserDetailsImpl(private val user: User): UserDetails {
    private val serialVersionUID = 1L

    override fun getAuthorities(): Collection<GrantedAuthority>? {
        return user.getRoles()!!.stream().map { role: String? -> SimpleGrantedAuthority(role) }
            .collect(Collectors.toList())
    }

    override fun getPassword(): String = user.getPassword()

    override fun getUsername(): String = user.getName()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = user.isEnabled()
}