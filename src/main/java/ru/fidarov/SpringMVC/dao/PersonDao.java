package ru.fidarov.SpringMVC.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.fidarov.SpringMVC.models.Person;

import java.util.*;

@Component
public class PersonDao {

    private final SessionFactory sessionFactory;
    private List<Person> tempData;
    {
        tempData = new ArrayList<>();
        tempData.add(new Person("Default",1));
    }
    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Transactional
    public Person show(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class,id);
    }

    //создаёт транзакцию внутри него будет открыта транзакция
    @Transactional(readOnly = true)//указываем на то что только изменяем
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("SELECT p FROM Person p ", Person.class)
                .getResultList();
    }
    @Transactional
    public void save(Person person){
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
    }
    @Transactional
    public void update(int id,Person updatedPerson){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class,id);
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
        session.update(person);
    }
    @Transactional
    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class,id);
        session.delete(person);

    }
    @Transactional
    public void setFavourite(int id){
        Session session = sessionFactory.getCurrentSession();
        Person favourite = session.get(Person.class,id);
        tempData.add(favourite);
        if (tempData.size()>5){
            tempData.clear();
        }
        System.out.println(tempData.get(tempData.size()-1).getName());
    }
    @Transactional
    public String getFavourite(){
        System.out.println(tempData.get(tempData.size()-1).getName());
        return tempData.get(tempData.size()-1).getName();
    }
}
