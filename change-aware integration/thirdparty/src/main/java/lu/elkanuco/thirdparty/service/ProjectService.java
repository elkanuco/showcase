package lu.elkanuco.thirdparty.service;


import lombok.RequiredArgsConstructor;


import lu.elkanuco.thirdparty.entity.Project;
import lu.elkanuco.thirdparty.mapper.ProjectMapper;
import lu.elkanuco.thirdparty.repository.ProjectRepository;
import lu.elkanuco.common.dto.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository repository;
    private final ProjectMapper projectMapper;

    public List<Project> findAll() {
        return repository.findAll();
    }

    public Optional<Project> findById(UUID id) {
        return repository.findById(id);
    }

    public Project save(Project project) {
        return repository.save(project);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
    
    public Optional<ProjectDto> getProjectWithPersonsAndRoles(UUID projectId) {
        return repository.findById(projectId)
                .map(projectMapper::toDto);
    }
    
    public List<ProjectDto> getProjectWithPersonsAndRoles() {
        return repository.findAll()
        		.stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }
}
