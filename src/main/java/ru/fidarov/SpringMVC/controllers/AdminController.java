package ru.fidarov.SpringMVC.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fidarov.SpringMVC.dao.PersonDao;
import ru.fidarov.SpringMVC.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDao personDao;
    @Autowired
    public AdminController(PersonDao personDao) {
        this.personDao = personDao;
    }
    @GetMapping()
    public String getAdminPage(Model model, @ModelAttribute("person")Person person){
        model.addAttribute("people",personDao.index());

        return "adminPage";
    }
    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        System.out.println("Your favourite PornStar is: " + person.getName());
        return "redirect:/people";
    }
}
