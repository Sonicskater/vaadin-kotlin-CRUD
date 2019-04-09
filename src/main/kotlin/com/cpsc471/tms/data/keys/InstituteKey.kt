package com.cpsc471.tms.data.keys


import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.ui.components.DisplayOld
import com.cpsc471.tms.ui.components.Editable
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

) : DBKey() {
        override fun iDforDb(): List<*> {
                return listOf(name,postalCode)
        }
}