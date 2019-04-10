package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.types.School
import java.time.LocalDate
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Embeddable
class ProjectKey(


        @Display(editLevel = DisplayEditLevel.CREATABLE, category = DisplayCategory.NORMAL)
        var start: LocalDate = LocalDate.of(1900,1,1),

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        var end: LocalDate = LocalDate.of(1900,1,1),

        @Display(editLevel = DisplayEditLevel.CREATABLE, type = School::class, clasif = DisplayTypeClasif.OBJECT)
        @ManyToOne(fetch = FetchType.LAZY,targetEntity = School::class)
        var school : School = School()

        ) : DBKey(){

    override fun iDforDb(): List<*> {
        return listOf(start,end,school)
    }

}