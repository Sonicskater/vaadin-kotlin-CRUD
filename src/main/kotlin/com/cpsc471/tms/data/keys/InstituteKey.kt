package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.ui.components.Display
import com.cpsc471.tms.ui.components.Editable
import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class InstituteKey(
        @Display
        @Editable(true)
        var name : String = "",
        @Display
        @Editable(true)
        var postalCode : String = ""
) : Serializable, DBAbstract() {
        override fun IDforDb(): List<Any> {
                return listOf(name,postalCode)
        }
}