package com.cpsc471.tms.data.repository.institute


import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.repository.DBKey
import javax.persistence.Embeddable

@Embeddable
class InstituteKey: DBKey() {

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var name : String? = null

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var postalCode : String? = null

        override fun iDforDb(): List<*> {
                return listOf(name,postalCode)
        }


}