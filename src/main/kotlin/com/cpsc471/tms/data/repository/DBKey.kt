package com.cpsc471.tms.data.repository

import java.io.Serializable


abstract class DBKey : Serializable, IDBValue {
    override fun equals(other: Any?): Boolean {
        return if (other is DBKey){
            other.iDforDb() == this.iDforDb()
        } else{
            false
        }
    }

    override fun hashCode(): Int {
        return 32
    }

}