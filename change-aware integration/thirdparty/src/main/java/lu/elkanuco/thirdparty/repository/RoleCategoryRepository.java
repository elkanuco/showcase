package lu.elkanuco.thirdparty.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lu.elkanuco.thirdparty.entity.RoleCategory;

import java.util.UUID;

public interface RoleCategoryRepository extends JpaRepository<RoleCategory, UUID> {
}
