package com.cpsc471.tms.data.types

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("2")
class FundingSource(
        @OneToMany(targetEntity = SelfGrant::class,mappedBy = "source")
        var selfGrants: MutableList<SelfGrant> = mutableListOf(),
        @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "source")
        var applicationSchools: MutableList<SchoolGrantApplication> = mutableListOf()
) : Institute() {
        override fun delete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
}