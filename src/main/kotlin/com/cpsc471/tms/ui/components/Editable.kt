package com.cpsc471.tms.ui.components

@Target(AnnotationTarget.FIELD,AnnotationTarget.FIELD)
annotation class Editable(
        val required : Boolean = false,
        val primitive: Boolean = true
)