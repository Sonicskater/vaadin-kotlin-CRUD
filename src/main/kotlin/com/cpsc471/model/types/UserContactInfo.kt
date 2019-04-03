package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "user_contact_infos")
data class UserContactInfo(
        @Id var UserPhoneNumber: String,
        var UserDescription: String,

        @Id
        @ManyToOne(fetch = FetchType.LAZY, targetEntity = User::class)
        var user:User
) : Serializable
