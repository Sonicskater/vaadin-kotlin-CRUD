package com.cpsc471.tms.data.types

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.keys.DBKey
import org.springframework.data.jpa.repository.Temporal
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.Repository
import java.time.LocalDate
import java.util.*

import javax.persistence.*

@Entity
@DiscriminatorValue("1")
class Artist(
        @ManyToMany(targetEntity = Project::class, mappedBy = "members")
        var projects: MutableList<Project> = mutableListOf(),

        @Basic
        @ElementCollection
        @Temporal(TemporalType.DATE)
        var availableDays: MutableSet<LocalDate> = mutableSetOf(),

        @Basic
        @ElementCollection
        var notes: MutableList<String> = mutableListOf()
) : User() {
        override fun <T, ID> getRepo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
                return RepoHelper.artistRepository as CrudRepository<T, ID>
        }
}