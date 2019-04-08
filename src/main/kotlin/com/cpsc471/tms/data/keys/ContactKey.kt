package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.types.Institute
import com.cpsc471.tms.ui.components.DisplayOld
import com.cpsc471.tms.ui.components.Editable
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class ContactKey(
        @DisplayOld
        @Editable(true)
        @ManyToOne(fetch = FetchType.LAZY, targetEntity = Institute::class)
        var institute: Institute,

        @DisplayOld
        @Editable(true)
        var email: String
        ) : Serializable, DBAbstract() {
    override fun IDforDb(): List<Any> {
        return listOf(institute, email)
    }
}