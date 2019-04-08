package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.DBAbstract
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.ui.components.DisplayOld
import com.cpsc471.tms.ui.components.Editable
import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class InstituteKey(

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        @DisplayOld
        @Editable(true)
        var name : String = "",
        @Display(editLevel = DisplayEditLevel.CREATABLE)
        @DisplayOld
        @Editable(true)
        var postalCode : String = ""
) : Serializable, DBAbstract() {
        override fun IDforDb(): List<Any> {
                return listOf(name,postalCode)
        }
}