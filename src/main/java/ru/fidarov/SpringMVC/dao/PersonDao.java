package ru.fidarov.SpringMVC.dao;

import org.springframework.stereotype.Component;
import ru.fidarov.SpringMVC.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {
    private static int PEOPLE_COUNT;
    private final List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT,"Georgii","fidarov@gmail.com",27));
        people.add(new Person(++PEOPLE_COUNT,"Nina","nina@yandex.com",27));
        people.add(new Person(++PEOPLE_COUNT,"Ilina","ilina@mail.com",14));
        people.add(new Person(++PEOPLE_COUNT,"Sveta","svetafindarova@gmail.com",20));

    }
    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId()==id).findAny().orElse(null);
    }
    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    public void update(int id,Person updatedPerson){
        Person ToBeUpdatedPerson = show(id);
        ToBeUpdatedPerson.setName(updatedPerson.getName());
        ToBeUpdatedPerson.setAge(updatedPerson.getAge());
        ToBeUpdatedPerson.setEmail(updatedPerson.getEmail());

    }
    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }



}
