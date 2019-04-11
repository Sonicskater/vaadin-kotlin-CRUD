package com.cpsc471.tms.data.repository.contacts

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.institute.Institute
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class ContactKey: Serializable, DBKey() {



    @Display(editLevel = DisplayEditLevel.CREATABLE)
    var email: String? = null

    @Display(clasif = DisplayTypeClasif.OBJECT, editLevel = DisplayEditLevel.CREATABLE, type = Institute::class)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Institute::class)
    var institute: Institute? = null

    override fun iDforDb(): List<Any?> {
        return listOf(institute, email)
    }
}