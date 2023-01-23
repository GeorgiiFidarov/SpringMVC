package ru.fidarov.SpringMVC.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fidarov.SpringMVC.models.Movie;
import ru.fidarov.SpringMVC.models.Person;

import java.util.List;

@Repository
public interface MoviesRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findByName(String movieName);

    List<Movie> findByOwner(Person owner);
}
