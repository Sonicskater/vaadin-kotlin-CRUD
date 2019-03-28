package com.cpsc471.model.types

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("2")
class FundingSource(
        @OneToMany(targetEntity = SelfGrant::class,mappedBy = "source")
        var selfGrants: MutableList<SelfGrant>,
        @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "source")
        var applicationSchools: MutableList<SchoolGrantApplication>
) : Institute()