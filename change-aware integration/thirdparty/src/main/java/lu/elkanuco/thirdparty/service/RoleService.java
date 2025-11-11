package lu.elkanuco.thirdparty.service;


import lombok.RequiredArgsConstructor;
import lu.elkanuco.thirdparty.entity.Role;
import lu.elkanuco.thirdparty.repository.RoleRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public List<Role> findAll() {
        return repository.findAll();
    }

    public Optional<Role> findById(UUID id) {
        return repository.findById(id);
    }

    public Role save(Role role) {
        return repository.save(role);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
