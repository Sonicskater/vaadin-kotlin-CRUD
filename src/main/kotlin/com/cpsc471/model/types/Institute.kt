package com.cpsc471.model.types

import javax.persistence.*

@MappedSuperclass
abstract class Institute(
    @Id var Country:String,
    @Id var Postal_code:String,
    @Id var Street_address:String,
    @Id var City:String,
    @Id var Province:String,
    var Website:String,
    var Type:Int,
    var Name:String
): DBAbstract(){
    override fun getID(): List<Any> {
        return listOf(Country, Postal_code, Street_address, City, Province)
    }

}