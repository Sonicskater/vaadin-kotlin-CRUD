package com.cpsc471.tms.data.repository.selfGrants

import org.springframework.data.repository.CrudRepository

interface SelfGrantRepository : CrudRepository<SelfGrant, SelfGrantKey> {
}