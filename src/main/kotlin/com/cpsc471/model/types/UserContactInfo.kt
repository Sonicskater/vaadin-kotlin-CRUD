package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_contact_infos")
data class UserContactInfo(
        @Id var Usr_email: String,
        @Id var Usr_phone_number: String,
        var Usr_description: String,

        @ManyToOne(fetch = FetchType.LAZY, targetEntity = User::class)
        var user:User
) : Serializable
