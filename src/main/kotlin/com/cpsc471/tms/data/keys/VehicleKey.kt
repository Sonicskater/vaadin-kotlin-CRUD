package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import javax.persistence.Embeddable

@Embeddable
class VehicleKey(
        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var licensePlate: String = ""
        ) : DBKey() {
    override fun iDforDb(): List<*> {
        return listOf(licensePlate)
    }
}