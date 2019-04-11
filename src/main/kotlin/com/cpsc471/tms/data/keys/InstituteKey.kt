package com.cpsc471.tms.data.keys


import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import javax.persistence.Embeddable

@Embeddable
class InstituteKey: DBKey() {

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var name : String = ""

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var postalCode : String =""

        override fun iDforDb(): List<*> {
                return listOf(name,postalCode)
        }


}