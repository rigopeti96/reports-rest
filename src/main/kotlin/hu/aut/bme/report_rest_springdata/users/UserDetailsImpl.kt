package hu.aut.bme.report_rest_springdata.users

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserDetailsImpl(private val user: User): UserDetails {
    private val serialVersionUID = 1L

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return user.getRoles()!!.stream().map { role: String? -> SimpleGrantedAuthority(role) }
            .collect(Collectors.toList())
    }

    override fun getPassword(): String {
        return user.getPassword()
    }

    override fun getUsername(): String {
        return user.getName()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return user.isEnabled()
    }
}