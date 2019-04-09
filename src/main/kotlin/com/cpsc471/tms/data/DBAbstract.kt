package com.cpsc471.tms.data

import com.cpsc471.tms.data.keys.DBKey
import org.springframework.data.repository.CrudRepository


abstract class DBAbstract : IDBValue{

    override fun hashCode(): Int {
        return 31
    }

    override fun equals(other: Any?): Boolean {
        return if(other !is DBAbstract){
            false
        }
        else{
            val vSet = other.iDforDb()
            if (this::class == other::class){
                vSet == this.iDforDb()
            } else{
                false
            }
        }
    }

    abstract fun <T,ID>getRepo( classT : Class<T>, classID : Class<ID>) : CrudRepository<T, ID>

    abstract fun getKeyType() : Class<out DBKey>


}