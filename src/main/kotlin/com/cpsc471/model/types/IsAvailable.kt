package com.cpsc471.model.types

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="is_available")
data class IsAvailable(
        @Id var Month:String,
        @Id var Number: Int,
        @Id var Year: Int,
        @Id var Email: String

) : Serializable