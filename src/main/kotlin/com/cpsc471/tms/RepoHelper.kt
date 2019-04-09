package com.cpsc471.tms

import com.cpsc471.tms.data.repos.ArtistRepository
import com.cpsc471.tms.data.repos.ManagerRepository
import com.cpsc471.tms.data.repos.ProjectRepository
import com.cpsc471.tms.data.repos.SchoolRepository
import com.sun.media.sound.SoftChorus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Service

object RepoHelper{

    lateinit var schoolRepository: SchoolRepository

    lateinit var projectRepository: ProjectRepository

    lateinit var artistRepository: ArtistRepository

    lateinit var managerRepository: ManagerRepository
}
