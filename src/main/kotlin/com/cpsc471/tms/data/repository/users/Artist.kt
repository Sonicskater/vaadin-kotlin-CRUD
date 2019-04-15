package com.cpsc471.tms.data.repository.users

import com.cpsc471.tms.RepoHelper
import com.cpsc471.tms.data.annotations.Display
import com.cpsc471.tms.data.annotations.DisplayTypeClasif
import com.cpsc471.tms.data.repository.projects.Project
import com.cpsc471.tms.ui.crudpages.ArtistView
import com.vaadin.flow.component.UI
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import javax.persistence.*

@Entity
@DiscriminatorValue("1")
class Artist(): User() {
        override fun view(ui: UI) {
                ui.navigate(ArtistView::class.java)
        }

        @Display(DisplayTypeClasif.LIST, type = Project::class)
        @ManyToMany(targetEntity = Project::class, mappedBy = "members", cascade = [CascadeType.ALL])
        var projects: MutableList<Project> = mutableListOf()

        @Display(DisplayTypeClasif.LIST, type_other = LocalDate::class)
        @Basic
        @ElementCollection
        var availableDays: MutableList<LocalDate> = mutableListOf()

        @Basic
        @ElementCollection
        var notes: MutableList<String> = mutableListOf()

        override fun delete() {
                RepoHelper.artistRepository.deleteById(this.userKey)
        }

        override fun <T, ID> repo(classT: Class<T>, classID: Class<ID>): CrudRepository<T, ID> {
                return RepoHelper.artistRepository as CrudRepository<T, ID>
        }

        constructor(user : User) : this() {
                this.city = user.city
                this.contactInfo = user.contactInfo
                this.country = user.country
                this.userKey = user.userKey
                this.firstName = user.firstName
                this.lastName = user.lastName
                this.password = user.password
                this.postalCode = user.postalCode
                this.province = user.province
                this.streetAddress = user.streetAddress
                RepoHelper.userRepository.deleteById(userKey)
        }
}