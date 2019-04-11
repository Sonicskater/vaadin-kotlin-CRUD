package com.cpsc471.tms.data.repository.users

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.repository.projects.Project
import com.vaadin.flow.data.binder.Validator
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import javax.persistence.*

@Entity
@DiscriminatorValue("1")
class Artist: User() {
        @ManyToMany(targetEntity = Project::class, mappedBy = "members")
        var projects: MutableList<Project> = mutableListOf()

        @Basic
        @ElementCollection
        var availableDays: MutableSet<LocalDate> = mutableSetOf()

        @Basic
        @ElementCollection
        var notes: MutableList<String> = mutableListOf()

        override fun delete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun <T> validator(clazz: Class<T>, creation: Boolean): Validator<in T>? {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
                return RepoHelper.artistRepository as CrudRepository<T, ID>
        }
}