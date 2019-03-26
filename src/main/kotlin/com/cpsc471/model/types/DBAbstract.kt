package com.cpsc471.model.types

import org.omg.CORBA.Object

abstract class DBAbstract {

    companion object {
        private val serialVersionUID = -5554308939380869754L
    }

    override fun hashCode(): Int {
        return 31
    }

    override fun equals(other: Any?): Boolean {
        if(!(other is DBAbstract)){
            return false
        }
        else{
            val vSet = other.getID()
            if (this::class == other::class){
                return vSet == this.getID()
            } else{
                return false
            }
        }
    }
    abstract fun getID() : List<Any>
}