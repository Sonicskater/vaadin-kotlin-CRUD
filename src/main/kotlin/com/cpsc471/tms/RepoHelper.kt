package com.cpsc471.tms

import com.cpsc471.tms.data.repos.ArtistRepository
import com.cpsc471.tms.data.repos.ManagerRepository
import com.cpsc471.tms.data.repos.ProjectRepository
import com.cpsc471.tms.data.repos.SchoolRepository

object RepoHelper{

    lateinit var schoolRepository: SchoolRepository

    lateinit var projectRepository: ProjectRepository

    lateinit var artistRepository: ArtistRepository

    lateinit var managerRepository: ManagerRepository
}
