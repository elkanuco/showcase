package lu.elkanuco.thirdparty.service;


import lombok.RequiredArgsConstructor;
import lu.elkanuco.thirdparty.entity.Person;
import lu.elkanuco.thirdparty.repository.PersonRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;


    public List<Person> findAll() {
        return repository.findAll();
    }

    public Optional<Person> findById(UUID id) {
        return repository.findById(id);
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
    
}
