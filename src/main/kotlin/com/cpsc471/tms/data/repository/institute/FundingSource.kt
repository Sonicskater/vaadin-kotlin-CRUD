package com.cpsc471.tms.data.repository.institute

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.repository.schoolGrantApplications.SchoolGrantApplication
import com.cpsc471.tms.data.repository.selfGrants.SelfGrant
import org.springframework.data.repository.CrudRepository
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("2")
class FundingSource : Institute() {
        override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
                return RepoHelper.fundingSourceRepository as CrudRepository<T, ID>
        }

        @OneToMany(targetEntity = SelfGrant::class,mappedBy = "selfGrantKey.source")
        var selfGrants: MutableList<SelfGrant> = mutableListOf()

        @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "schoolGrantApplicationKey.source")
        var schoolApplications: MutableList<SchoolGrantApplication> = mutableListOf()


        override fun delete() {
                RepoHelper.instituteRepository.deleteById(this.instituteKey)
        }
}