package lu.elkanuco.thirdparty.service;

import lombok.RequiredArgsConstructor;
import lu.elkanuco.thirdparty.dto.CreatePersonAssignmentDTO;
import lu.elkanuco.thirdparty.entity.ProjectAssignment;
import lu.elkanuco.thirdparty.mapper.PersonAssignmentMapper;
import lu.elkanuco.thirdparty.repository.PersonRepository;
import lu.elkanuco.thirdparty.repository.ProjectAssignmentRepository;
import lu.elkanuco.thirdparty.repository.ProjectRepository;
import lu.elkanuco.thirdparty.repository.RoleRepository;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectAssignmentService {
    private final ProjectAssignmentRepository projectAssignmentRepository;
    private final PersonRepository personRepository;
    private final ProjectRepository projectRepository;
    private final RoleRepository roleRepository;
    private final PersonAssignmentMapper mapper;

    public List<ProjectAssignment> findAll() {
        return projectAssignmentRepository.findAll();
    }

    public Optional<ProjectAssignment> findById(UUID id) {
        return projectAssignmentRepository.findById(id);
    }

    public ProjectAssignment save(ProjectAssignment projectAssignment) {
        return projectAssignmentRepository.save(projectAssignment);
    }

    public void deleteById(UUID id) {
    	projectAssignmentRepository.deleteById(id);
    }
    
    @Transactional
    public ProjectAssignment createAssignment(CreatePersonAssignmentDTO dto) {
        ProjectAssignment assignment = mapper.toProjectAssignment(dto);
        assignment.setPerson(personRepository.save(assignment.getPerson()));
        assignment.setProject(projectRepository.getReferenceById(dto.getProjectId()));
        assignment.setRole(roleRepository.getReferenceById(dto.getRoleId()));
        return projectAssignmentRepository.save(assignment);
    }
}
