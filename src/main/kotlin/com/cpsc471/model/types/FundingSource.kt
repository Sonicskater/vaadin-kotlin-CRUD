package com.cpsc471.model.types

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "funding_source")
class FundingSource (

) : Institute()