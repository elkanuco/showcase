package lu.elkanuco.thirdparty.mapper;

import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import lu.elkanuco.thirdparty.dto.CreatePersonAssignmentDTO;
import lu.elkanuco.thirdparty.entity.Person;
import lu.elkanuco.thirdparty.entity.Project;
import lu.elkanuco.thirdparty.entity.ProjectAssignment;
import lu.elkanuco.thirdparty.entity.Role;

@Mapper(componentModel = "spring")
public interface PersonAssignmentMapper {
	
	@Mapping(target = "person.fullname", source = "dto.fullname")
	@Mapping(target = "person.pictureurl", source = "dto.pictureurl")
	@Mapping(target = "project", expression = "java(fetchProjectReference(dto.getProjectId()))")
	@Mapping(target = "role", expression = "java(fetchRoleReference(dto.getRoleId()))")
	ProjectAssignment toProjectAssignment(CreatePersonAssignmentDTO dto);

	default Person toPerson(CreatePersonAssignmentDTO dto) {
		if (dto == null) {
			return null;
		}
		Person person = new Person();
		person.setFullname(dto.getFullname());
		person.setPictureurl(dto.getPictureurl());
		return person;
	}

	default Project fetchProjectReference(UUID projectId) {
		Project p = new Project();
		p.setId(projectId);
		return p;
	}

	default Role fetchRoleReference(UUID roleId) {
		Role r = new Role();
		r.setId(roleId);
		return r;
	}

	@AfterMapping
	default void linkEntities(@MappingTarget ProjectAssignment assignment, CreatePersonAssignmentDTO dto) {
		Person person = toPerson(dto);
		assignment.setPerson(person);
	}
}