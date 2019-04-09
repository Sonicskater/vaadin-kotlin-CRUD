package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import javax.persistence.Embeddable
import javax.persistence.Id

@Embeddable
class UserKey(
    @Id
    @Display(editLevel =  DisplayEditLevel.CREATABLE)
    var email: String = ""

) : DBKey(){
    override fun iDforDb(): List<*> {
        return listOf(email)
    }

}
