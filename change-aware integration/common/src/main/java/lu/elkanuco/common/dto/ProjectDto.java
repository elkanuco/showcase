package lu.elkanuco.common.dto;

import java.util.List;
import java.util.UUID;

public record ProjectDto(
    UUID id,
    String name,
    String description,
    List<RoleCategoryGroup> roleCategoryGroups
) {
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private UUID id;
        private String name;
        private String description;
        private List<RoleCategoryGroup> roleCategoryGroups;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder roleCategoryGroups(List<RoleCategoryGroup> roleCategoryGroups) {
            this.roleCategoryGroups = roleCategoryGroups;
            return this;
        }
        public ProjectDto build() {
            return new ProjectDto(id, name, description, roleCategoryGroups);
        }
    }

    public record RoleCategoryGroup(
        UUID categoryId,
        String categoryName,
        int hierarchyLevel,
        List<PersonRoleGroup> personRoleGroups
    ) {
        public static Builder builder() {
            return new Builder();
        }
        public static class Builder {
            private UUID categoryId;
            private String categoryName;
            private int hierarchyLevel;
            private List<PersonRoleGroup> personRoleGroups;

            public Builder categoryId(UUID categoryId) { this.categoryId = categoryId; return this; }
            public Builder categoryName(String categoryName) { this.categoryName = categoryName; return this; }
            public Builder hierarchyLevel(int hierarchyLevel) { this.hierarchyLevel = hierarchyLevel; return this; }
            public Builder personRoleGroups(List<PersonRoleGroup> personRoleGroups) {
                this.personRoleGroups = personRoleGroups;
                return this;
            }
            public RoleCategoryGroup build() {
                return new RoleCategoryGroup(categoryId, categoryName, hierarchyLevel, personRoleGroups);
            }
        }
    }

    public record PersonRoleGroup(
        UUID personId,
        String fullName,
        String pictureUrl,
        List<RoleDto> roles
    ) {
        public static Builder builder() {
            return new Builder();
        }
        public static class Builder {
            private UUID personId;
            private String fullName;
            private String pictureUrl;
            private List<RoleDto> roles;

            public Builder personId(UUID personId) { this.personId = personId; return this; }
            public Builder fullName(String fullName) { this.fullName = fullName; return this; }
            public Builder pictureUrl(String pictureUrl) { this.pictureUrl = pictureUrl; return this; }
            public Builder roles(List<RoleDto> roles) { this.roles = roles; return this; }
            public PersonRoleGroup build() {
                return new PersonRoleGroup(personId, fullName, pictureUrl, roles);
            }
        }
    }

    public record RoleDto(
        UUID roleId,
        String roleName,
        String roleDescription,
        int roleHierarchyLevel
    ) {
        public static Builder builder() {
            return new Builder();
        }
        public static class Builder {
            private UUID roleId;
            private String roleName;
            private String roleDescription;
            private int roleHierarchyLevel;

            public Builder roleId(UUID roleId) { this.roleId = roleId; return this; }
            public Builder roleName(String roleName) { this.roleName = roleName; return this; }
            public Builder roleDescription(String roleDescription) { this.roleDescription = roleDescription; return this; }
            public Builder roleHierarchyLevel(int roleHierarchyLevel) { this.roleHierarchyLevel = roleHierarchyLevel; return this; }
            public RoleDto build() {
                return new RoleDto(roleId, roleName, roleDescription, roleHierarchyLevel);
            }
        }
    }
}


