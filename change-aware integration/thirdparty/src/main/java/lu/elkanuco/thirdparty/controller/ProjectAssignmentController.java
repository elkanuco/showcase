package lu.elkanuco.thirdparty.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lu.elkanuco.common.dto.ProjectDto;
import lu.elkanuco.thirdparty.dto.CreatePersonAssignmentDTO;
import lu.elkanuco.thirdparty.event.ProjectDtoKafkaProducer;
import lu.elkanuco.thirdparty.service.ProjectAssignmentService;
import lu.elkanuco.thirdparty.service.ProjectService;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class ProjectAssignmentController {

	private final ProjectAssignmentService assignmentService;
	private final ProjectService projectService;
	private final ProjectDtoKafkaProducer kafkaProducer;

	@PostMapping
	public ResponseEntity<ProjectDto> addPersonToProject(@RequestBody CreatePersonAssignmentDTO dto) {
		assignmentService.createAssignment(dto);
		ProjectDto result = projectService.getProjectWithPersonsAndRoles(dto.getProjectId()).orElseThrow();
		kafkaProducer.sendProjectEvent(result);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
}
