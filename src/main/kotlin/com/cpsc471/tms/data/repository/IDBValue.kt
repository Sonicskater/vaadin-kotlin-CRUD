package com.cpsc471.tms.data.repository

interface IDBValue {
    override fun hashCode(): Int

    override fun equals(other: Any?): Boolean
    fun iDforDb() : List<Any?>
}