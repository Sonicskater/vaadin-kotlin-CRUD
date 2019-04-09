package com.cpsc471.tms.ui.components

import kotlin.reflect.KClass
@Deprecated("")
@Target(AnnotationTarget.FIELD,AnnotationTarget.FIELD)
annotation class EditableList(    val type: KClass<*>) {

}