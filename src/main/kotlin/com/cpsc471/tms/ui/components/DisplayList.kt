package com.cpsc471.tms.ui.components

import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD,AnnotationTarget.FIELD)
annotation class DisplayList(val type : KClass<*>)