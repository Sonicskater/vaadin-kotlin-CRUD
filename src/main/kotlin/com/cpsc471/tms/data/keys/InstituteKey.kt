package com.cpsc471.tms.data.keys


import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import javax.persistence.Embeddable

@Embeddable
class InstituteKey(

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var name : String = "",

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var postalCode : String = ""

) : DBKey() {
        override fun iDforDb(): List<*> {
                return listOf(name,postalCode)
        }
}