package com.cpsc471.tms.data.types

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.data.repository.CrudRepository
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
@DiscriminatorValue("1")
@Configurable(dependencyCheck = true)
open class School (

        @Display
    open var gradeMin:Int = 0,

        @Display
    open var gradeMax:Int = 0,

        @Display(type = Project::class,clasif = DisplayTypeClasif.LIST)
    @OneToMany(targetEntity = Project::class, mappedBy = "projectKey.school")
    open var projects: MutableList<Project> = mutableListOf(),

        @Display(type = SchoolGrantApplication::class,clasif = DisplayTypeClasif.LIST)
    @OneToMany(targetEntity = SchoolGrantApplication::class, mappedBy = "applicant")
    open var applicationSchools: MutableList<SchoolGrantApplication> = mutableListOf()


) : Institute() {

    override fun delete() {
        RepoHelper.schoolRepository.deleteById(instituteKey)
    }
    override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
        return RepoHelper.schoolRepository as CrudRepository<T, ID>
    }

}
