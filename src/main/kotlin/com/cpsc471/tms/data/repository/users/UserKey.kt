package com.cpsc471.tms.data.repository.users

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.repository.DBKey
import javax.persistence.Embeddable

@Embeddable
class UserKey: DBKey(){

    @Display(editLevel =  DisplayEditLevel.CREATABLE)
    var email: String? = null

    override fun iDforDb(): List<*> {
        return listOf(email)
    }

}
