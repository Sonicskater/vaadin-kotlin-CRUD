package com.cpsc471.tms.data.repository.projects

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : CrudRepository<Project, ProjectKey>{


}