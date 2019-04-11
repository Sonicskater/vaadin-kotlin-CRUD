package com.cpsc471.tms.data.types

import com.cpsc471.tms.data.IDBValue
import com.cpsc471.tms.data.keys.DBKey
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import javax.persistence.Transient


abstract class DBAbstract : IDBValue {

    @Transient
    override fun hashCode(): Int {
        return 31
    }

    @Transient
    override fun equals(other: Any?): Boolean {
        return if(other !is DBAbstract){
            false
        }
        else{
            val vSet = other.iDforDb()
            if (this::class == other::class){
                vSet == (this.iDforDb())
            } else{
                false
            }
        }
    }

    @Transient
    abstract fun <T,ID>repo(classT : Class<T>, classID : Class<ID>) : CrudRepository<T, ID>

    @Transient
    abstract fun keyType() : Class<out DBKey>

    @Transient
    abstract fun <T>validator(clazz: Class<T>, creation : Boolean) : Validator<in T>?

    @Transient
    abstract fun delete()


}