package hu.aut.bme.report_rest_springdata.collections

import hu.aut.bme.report_rest_springdata.users.ERole
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "roles")
class Role {
    @Id
    var id: String? = null

    @Indexed(unique = true)
    var name: ERole? = null

    constructor()
    constructor(name: ERole?) {
        this.name = name
    }
}