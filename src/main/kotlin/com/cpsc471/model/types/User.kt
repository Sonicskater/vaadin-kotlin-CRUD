package com.cpsc471.model.types
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        var First_name: String,
        var Last_name: String,
        @Id var Email: String,
        var Type: String,
        var Country: String,
        var Province: String,
        var City: String,
        var Street_address: String,
        var Postal_code: String,
        var User_type: String
)