package com.cpsc471.tms.data.repository.vehicles

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.repository.DBKey
import javax.persistence.Embeddable

@Embeddable
class VehicleKey: DBKey() {

    @Display(editLevel = DisplayEditLevel.CREATABLE)
    var licensePlate: String? = null

    override fun iDforDb(): List<*> {
        return listOf(licensePlate)
    }
}