package com.cpsc471.tms.data.repository.institute

import com.cpsc471.tms.data.repository.schoolGrantApplications.SchoolGrantApplication
import com.cpsc471.tms.data.repository.selfGrants.SelfGrant
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("2")
class FundingSource : Institute() {
        @OneToMany(targetEntity = SelfGrant::class,mappedBy = "source")
        var selfGrants: MutableList<SelfGrant> = mutableListOf()

        @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "source")
        var applicationSchools: MutableList<SchoolGrantApplication> = mutableListOf()

        override fun delete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
}