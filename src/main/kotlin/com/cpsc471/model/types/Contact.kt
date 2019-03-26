package com.cpsc471.model.types


import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "contact")
class Contact(
        @Id var Email: String
        ) : DBAbstract(){
    override fun getID() : List<Any> {
        return listOf(Email)
    }

}