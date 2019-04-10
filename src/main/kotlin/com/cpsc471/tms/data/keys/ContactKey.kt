package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.types.Institute
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class ContactKey(

        @Display(clasif = DisplayTypeClasif.OBJECT, editLevel = DisplayEditLevel.CREATABLE)
        @ManyToOne(fetch = FetchType.LAZY, targetEntity = Institute::class)
        var institute: Institute = Institute(),

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var email: String = ""
        ) : Serializable, DBKey() {
    override fun iDforDb(): List<Any> {
        return listOf(institute, email)
    }
}