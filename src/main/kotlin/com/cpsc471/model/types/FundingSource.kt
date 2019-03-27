package com.cpsc471.model.types

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@DiscriminatorValue("2")
class FundingSource(
        @OneToMany(targetEntity = Grant::class,mappedBy = "source")
        var grants: MutableList<Grant>,
        @OneToMany(targetEntity = GrantApplication::class, mappedBy = "source")
        var applications: MutableList<GrantApplication>
) : Institute()