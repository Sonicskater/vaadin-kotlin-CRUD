package com.cpsc471.tms.data.repos

import com.cpsc471.tms.data.keys.ProjectKey
import com.cpsc471.tms.data.types.Project
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.RepositoryDefinition
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : CrudRepository<Project, ProjectKey>{
}