package ru.fidarov.SpringMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fidarov.SpringMVC.models.Person;
import ru.fidarov.SpringMVC.services.MovieService;
import ru.fidarov.SpringMVC.services.PeopleService;


import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {


    private final PeopleService peopleService;
    private final MovieService movieService;

    @Autowired
    public PeopleController(PeopleService peopleService, MovieService movieService) {
        this.peopleService = peopleService;

        this.movieService = movieService;
    }
    @GetMapping
    public String index(Model model){
        model.addAttribute("people",peopleService.finaAll());
        model.addAttribute("fav",peopleService.getFavourite());

        movieService.findByMovieName("Hard Fuck in Fishnets");
        movieService.findByOwner(peopleService.finaAll().get(0));

        peopleService.test();

        return "/people/index";
    }
    @GetMapping("/{id}")
    public String show(
            @PathVariable("id") int id,
            Model model,
            @ModelAttribute("person") Person person){

        model.addAttribute("person",peopleService.findOne(id));//достали человека записали в модель
        model.addAttribute("movies",movieService.findByOwner(person));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person){
        peopleService.save(person);

        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")@Valid Person person,@PathVariable("id") int id){
        peopleService.update(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
