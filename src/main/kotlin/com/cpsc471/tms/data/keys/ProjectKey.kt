package com.cpsc471.tms.data.keys

import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayCategory
import com.cpsc471.tms.data.annotations.DisplayEditLevel
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.types.School
import com.cpsc471.tms.ui.components.DisplayList
import org.springframework.data.jpa.repository.Temporal
import java.io.Serializable
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Embeddable
class ProjectKey(


        @Display(editLevel = DisplayEditLevel.CREATABLE, category = DisplayCategory.NORMAL)
        @Temporal(TemporalType.DATE)
        var start: LocalDate = LocalDate.of(1900,1,1),

        @Display(editLevel = DisplayEditLevel.CREATABLE)
        @Temporal(TemporalType.DATE)
        var end: LocalDate = LocalDate.of(1900,1,1),

        @Display(editLevel = DisplayEditLevel.CREATABLE, type = School::class, clasif = DisplayTypeClasif.OBJECT)
        @ManyToOne(fetch = FetchType.LAZY,targetEntity = School::class)
        var school : School = School()

        ) : DBKey(){

    override fun iDforDb(): List<*> {
        return listOf(start,end,school)
    }

}