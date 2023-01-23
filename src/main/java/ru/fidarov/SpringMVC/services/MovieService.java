package ru.fidarov.SpringMVC.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fidarov.SpringMVC.models.Movie;
import ru.fidarov.SpringMVC.models.Person;
import ru.fidarov.SpringMVC.repositories.MoviesRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MovieService {
    private final MoviesRepository moviesRepository;

    @Autowired
    public MovieService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public List<Movie> findByMovieName(String movieName){
        return moviesRepository.findByName(movieName);
    }

    public List<Movie> findByOwner(Person owner){
        return moviesRepository.findByOwner(owner);
    }




}
