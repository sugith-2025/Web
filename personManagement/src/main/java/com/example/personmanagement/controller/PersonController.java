package com.example.personmanagement.controller;


import com.example.personmanagement.model.Person;
import com.example.personmanagement.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@CrossOrigin(origins = "http://localhost:5173")

public class PersonController {

    private final PersonRepository repo;

    public PersonController(PersonRepository repo)
    {
        this.repo=repo;
    }

    @GetMapping
    public List<Person> getAll(){
        return repo.findAll();
    }

    @PostMapping
    public Person add(@RequestBody Person person)
    {
        return repo.save(person);
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable String id,@RequestBody Person person)
    {
        person.setId(id);
        return repo.save(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id)
    {
        repo.deleteById(id);
    }
}
