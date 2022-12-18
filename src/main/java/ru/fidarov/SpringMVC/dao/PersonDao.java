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

        people.add(new Person(++PEOPLE_COUNT,"Georgii"));
        people.add(new Person(++PEOPLE_COUNT,"Nina"));
        people.add(new Person(++PEOPLE_COUNT,"Ilina"));
        people.add(new Person(++PEOPLE_COUNT,"Sveta"));

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


}
