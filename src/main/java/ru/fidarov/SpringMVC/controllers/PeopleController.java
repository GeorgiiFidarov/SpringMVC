package ru.fidarov.SpringMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fidarov.SpringMVC.dao.PersonDao;
import ru.fidarov.SpringMVC.models.Person;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;

    @Autowired
    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }
    //выводит список всех людей на скрин /people
    //c /people отправляет на view /people/index.html
    @GetMapping
    public String index(Model model){
        model.addAttribute("people",personDao.index());
        return "/people/index";
    }
    //показывает по id страницу каждого человека
    //страницу по человеку делает /people/show.html
    //@PathVariable->читает с html переменную и пишет ее в java код
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        //достаёт 1 человека по его Id
        model.addAttribute("person",personDao.show(id));//достали человека записали в модель
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/new";

        personDao.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDao.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")@Valid Person person,BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors()){
            return "people/edit";
        }
        personDao.update(id,person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDao.delete(id);
        return "redirect:/people";
    }

}
