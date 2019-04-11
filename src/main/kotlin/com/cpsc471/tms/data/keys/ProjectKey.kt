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
import javax.validation.constraints.NotNull

@Embeddable
class ProjectKey: DBKey(){

    @NotNull
    @Display(editLevel = DisplayEditLevel.CREATABLE, category = DisplayCategory.NORMAL)
    var start: LocalDate? = null

    @NotNull
    @Display(editLevel = DisplayEditLevel.CREATABLE)
    var end: LocalDate? = null

    @NotNull
    @Display(editLevel = DisplayEditLevel.CREATABLE, type = School::class, clasif = DisplayTypeClasif.OBJECT)
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = School::class)
    var school : School? = null

    override fun iDforDb(): List<*> {
        return listOf(start,end,school)
    }

}