package lu.elkanuco.thirdparty.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import lu.elkanuco.thirdparty.entity.ProjectAssignment;

import java.util.UUID;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, UUID> {
}
