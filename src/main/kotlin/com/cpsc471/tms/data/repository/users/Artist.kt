package com.cpsc471.tms.data.repository.users

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.projects.Project
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import javax.persistence.*

@Entity
@DiscriminatorValue("1")
class Artist: User() {
        @Display(DisplayTypeClasif.LIST, type = Project::class)
        @ManyToMany(targetEntity = Project::class, mappedBy = "members")
        var projects: MutableList<Project> = mutableListOf()

        @Basic
        @ElementCollection
        var availableDays: MutableSet<LocalDate> = mutableSetOf()

        @Basic
        @ElementCollection
        var notes: MutableList<String> = mutableListOf()

        override fun delete() {
                RepoHelper.artistRepository.deleteById(this.userKey)
        }

        override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
                return RepoHelper.artistRepository as CrudRepository<T, ID>
        }
}