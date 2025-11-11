package lu.elkanuco.thirdparty.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import lu.elkanuco.thirdparty.entity.Person;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
