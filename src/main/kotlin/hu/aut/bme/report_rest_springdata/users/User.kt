package hu.aut.bme.report_rest_springdata.users

import net.bytebuddy.implementation.bind.MethodDelegationBinder
import javax.persistence.*

/**
 * Felhasználókat reprezentáló adatosztály
 */
@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    private val name: String,
    private val password: String,
    private val enabled: Boolean,
    @ElementCollection(fetch = FetchType.EAGER)
    private val roles: List<String>?
) {
    constructor() : this(0, "", "", false, null) {

    }

    fun getName(): String = name
    fun getPassword(): String = password
    fun isEnabled(): Boolean = enabled
    fun getRoles(): List<String?>? = roles

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        result = prime * result + (name.hashCode() ?: 0)
        return result
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) return true
        if (obj == null) return false
        if (javaClass != obj.javaClass) return false
        val other: User = obj as User
        if (name != other.name) return false
        return true
    }

    companion object{
        const val ROLE_ADMIN = "ROLE_ADMIN"
    }
}