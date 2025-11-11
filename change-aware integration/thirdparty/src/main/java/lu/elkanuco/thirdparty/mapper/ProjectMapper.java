package lu.elkanuco.thirdparty.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import lu.elkanuco.common.dto.*;
import lu.elkanuco.common.dto.ProjectDto.PersonRoleGroup;
import lu.elkanuco.common.dto.ProjectDto.RoleCategoryGroup;
import lu.elkanuco.common.dto.ProjectDto.RoleDto;
import lu.elkanuco.thirdparty.entity.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    default ProjectDto toDto(Project project) {
        if (project == null) {
            return null;
        }
        Map<UUID, RoleCategoryGroup> categoryGroupMap = project.getAssignments().stream()
            .map(ProjectAssignment::getRole)
            .map(Role::getCategory)
            .distinct()
            .collect(Collectors.toMap(RoleCategory::getId, this::toRoleCategoryGroup));

        project.getAssignments().forEach(assignment -> {
            RoleCategoryGroup group = categoryGroupMap.get(assignment.getRole().getCategory().getId());
            if (group != null) {
                var personRoleGroup = toPersonRoleGroup(assignment);
                group.personRoleGroups().add(personRoleGroup);
            }
        });

        return ProjectDto.builder()
            .id(project.getId())
            .name(project.getName())
            .description(project.getDescription())
            .roleCategoryGroups(List.copyOf(categoryGroupMap.values()))
            .build();
    }

    default RoleCategoryGroup toRoleCategoryGroup(RoleCategory category) {
        return RoleCategoryGroup.builder()
            .categoryId(category.getId())
            .categoryName(category.getName())
            .hierarchyLevel(category.getHierarchylevel())
            .personRoleGroups(new java.util.ArrayList<>())
            .build();
    }

    default PersonRoleGroup toPersonRoleGroup(ProjectAssignment assignment) {
        Role role = assignment.getRole();
        return PersonRoleGroup.builder()
            .personId(assignment.getPerson().getId())
            .fullName(assignment.getPerson().getFullname())
            .pictureUrl(assignment.getPerson().getPictureurl())
            .roles(List.of(toRoleDto(role)))
            .build();
    }

    default RoleDto toRoleDto(Role role) {
        return RoleDto.builder()
            .roleId(role.getId())
            .roleName(role.getName())
            .roleDescription(role.getDescription())
            .roleHierarchyLevel(role.getHierarchylevel())
            .build();
    }

}
