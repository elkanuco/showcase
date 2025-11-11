package lu.elkanuco.thirdparty.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import lu.elkanuco.thirdparty.entity.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
