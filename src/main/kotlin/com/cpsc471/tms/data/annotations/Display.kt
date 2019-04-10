package com.cpsc471.tms.data.annotations

import com.cpsc471.tms.data.types.DBAbstract
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD,AnnotationTarget.FIELD)
annotation class Display(
        val clasif : DisplayTypeClasif = DisplayTypeClasif.PRIMITIVE,
        val category : DisplayCategory = DisplayCategory.NORMAL,
        val editLevel : DisplayEditLevel = DisplayEditLevel.EDITABLE,
        val type : KClass<out DBAbstract> = DBAbstract::class
)