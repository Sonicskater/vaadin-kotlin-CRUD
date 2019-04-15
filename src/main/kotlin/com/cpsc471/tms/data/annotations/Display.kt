package com.cpsc471.tms.data.annotations

import com.cpsc471.tms.data.repository.DBAbstract
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD,AnnotationTarget.FIELD)
annotation class Display(
        val clasif : DisplayTypeClasif = DisplayTypeClasif.PRIMITIVE,
        val category : DisplayCategory = DisplayCategory.NORMAL,
        val editLevel : DisplayEditLevel = DisplayEditLevel.EDITABLE,
        val type : KClass<out DBAbstract> = DBAbstract::class,
        val type_other : KClass<out Any> = Int::class
)