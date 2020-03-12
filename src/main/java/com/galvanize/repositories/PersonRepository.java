package com.galvanize.repositories;

import com.galvanize.entities.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonRepository {
    List<Person> people = new ArrayList<>();
    Long nextId = 10L;

    //init people
    {
        people.add(new Person(1L, "Barb", "barb@gmail.com", LocalDate.of(1962, 9,13) ));
        people.add(new Person(2L, "Dave", "dave@gmail.com", LocalDate.of(1990, 3,8) ));
        people.add(new Person(3L, "Jacqui", "jacqui@gmail.com", LocalDate.of(1989, 4,6) ));
    }

    public List<Person> getAllPeople(){
        return people;
    }

    public Person save(Person person){
        if (person.getId() == null) {
            person.setId(nextId++);
            people.add(person);
        }

        return person;
    }

    public Person update(Person person){
        Person p = findById(person.getId());
        if(p == null) {
            return save(person);
        }else{
            p.update(person);
            return p;
        }

    }

    public Person findById(Long id){
        for (Person p : people){
            if(p.getId() == id)
                return p;
        }

        return null;
    }

    public Person findByName(String name){
        for(Person person : people){
            if(person.getName().equalsIgnoreCase(name)){
                return person;
            }
        }

        return null;
    }

    public boolean delete(Long id) {
        for(Person p : people){
            if(p.getId() == id){
                people.remove(p);
                return true;
            }
        }
        return false;
    }
}
