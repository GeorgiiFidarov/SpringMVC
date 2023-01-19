package ru.fidarov.SpringMVC.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fidarov.SpringMVC.models.Person;
import ru.fidarov.SpringMVC.repositories.PeopleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private List<Person> listData;
    {
        listData = new ArrayList<>();
        listData.add(new Person("Def",1));
    }

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> finaAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }
    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
    @Transactional
    public void setFavourite(int id){
        Optional<Person> favourite = peopleRepository.findById(id);
        listData.add(favourite.orElse(null));
        listData.remove(listData.size()-2);

        System.out.println(listData.size());
    }
    public String getFavourite(){
        return listData.get(listData.size()-1).getName();
    }


}

















