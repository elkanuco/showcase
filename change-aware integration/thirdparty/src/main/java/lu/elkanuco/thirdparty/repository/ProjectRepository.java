package lu.elkanuco.thirdparty.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import lu.elkanuco.thirdparty.entity.Project;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
	
    @EntityGraph(attributePaths = {
        "assignments",
        "assignments.person",
        "assignments.role",
        "assignments.role.category"
    })
    Optional<Project> findById(UUID id);
}

