package lu.elkanuco.thirdparty.controller;

import lombok.RequiredArgsConstructor;
import lu.elkanuco.thirdparty.service.ProjectService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lu.elkanuco.common.dto.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectDetails(@PathVariable UUID id) {
        return projectService.getProjectWithPersonsAndRoles(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping()
    public ResponseEntity<List<ProjectDto>> getProjectDetails() {
    	return ResponseEntity.ok(projectService.getProjectWithPersonsAndRoles());
    }
}