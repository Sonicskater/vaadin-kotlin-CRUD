package com.cpsc471.tms.data.repository.institute

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.projects.Project
import com.cpsc471.tms.data.repository.schoolGrantApplications.SchoolGrantApplication
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.data.repository.CrudRepository
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("1")
@Configurable(dependencyCheck = true)
class School: Institute() {
    @Display
    var gradeMin:Int = 0

    @Display
    var gradeMax:Int = 0

    @Display(type = Project::class,clasif = DisplayTypeClasif.LIST)
    @OneToMany(targetEntity = Project::class, mappedBy = "projectKey.school")
    var projects: MutableList<Project> = mutableListOf()

    @Display(type = SchoolGrantApplication::class,clasif = DisplayTypeClasif.LIST)
    @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "applicant")
    var applicationSchools: MutableList<SchoolGrantApplication> = mutableListOf()


    override fun delete() {
        RepoHelper.schoolRepository.deleteById(instituteKey)
    }
    override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.schoolRepository as CrudRepository<T, ID>
    }

}
