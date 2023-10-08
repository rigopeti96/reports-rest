package hu.aut.bme.report_rest_springdata.collections

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.persistence.GeneratedValue
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * User-representation class
 */
@Document(collection = "users")
class User{
    @Id @GeneratedValue
    var id: ObjectId? = null
    var username: @NotBlank @Size(max = 20) String? = null
    var email: @NotBlank @Size(max = 50) @Email String? = null
    var password: @NotBlank @Size(max = 120) String? = null
    var enabled: @NotBlank Boolean = true

    @DBRef
    var roles: Set<Role> = HashSet()

    constructor()
    constructor(username: String?, email: String?, password: String?, enabled: Boolean) : super() {
        this.username = username
        this.email = email
        this.password = password
        this.enabled = enabled
    }
}