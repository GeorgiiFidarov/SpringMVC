package ru.fidarov.SpringMVC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.SpringMVC.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
