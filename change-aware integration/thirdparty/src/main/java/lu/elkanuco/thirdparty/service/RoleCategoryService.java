package lu.elkanuco.thirdparty.service;


import lombok.RequiredArgsConstructor;
import lu.elkanuco.thirdparty.entity.RoleCategory;
import lu.elkanuco.thirdparty.repository.RoleCategoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleCategoryService {
    private final RoleCategoryRepository repository;

    public List<RoleCategory> findAll() {
        return repository.findAll();
    }

    public Optional<RoleCategory> findById(UUID id) {
        return repository.findById(id);
    }

    public RoleCategory save(RoleCategory roleCategory) {
        return repository.save(roleCategory);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
