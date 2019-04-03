package com.cpsc471.tms.data.types

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
            val vSet = other.IDforDb()
            if (this::class == other::class){
                return vSet == this.IDforDb()
            } else{
                return false
            }
        }
    }
    abstract fun IDforDb() : List<Any>
}