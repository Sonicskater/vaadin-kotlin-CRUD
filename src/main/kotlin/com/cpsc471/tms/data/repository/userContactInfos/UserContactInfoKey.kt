package com.cpsc471.tms.data.repository.userContactInfos

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.DBKey
import com.cpsc471.tms.data.repository.users.User
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class UserContactInfoKey : DBKey(){
    override fun iDforDb(): List<Any?> {
        return listOf(user)
    }


    @Display(editLevel = DisplayEditLevel.CREATABLE)
    var phoneNumber: String? = null


    @Display(DisplayTypeClasif.OBJECT, type = User::class, editLevel = DisplayEditLevel.CREATABLE)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User::class)
    var user: User? = null
}
