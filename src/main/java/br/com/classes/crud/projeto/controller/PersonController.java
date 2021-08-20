package br.com.classes.crud.projeto.controller;


import br.com.classes.crud.projeto.dao.PersonDAO;
import br.com.classes.crud.projeto.model.Person;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PersonController implements Serializable {


    private Person person;
    private PersonDAO personDAO;
    private List<Person> personList;

    public PersonController() {
        this.person = new Person();
        this.personDAO = new PersonDAO();
    }

    public void signUpPerson() {
        this.personDAO.signUpPerson(this.person);
        this.clearInfo();

    }

    public void updatePerson() {
        this.personDAO.updatePerson(this.person);
    }

    public List<Person> getPersonList() {
        if (this.personList == null)
            this.personList = this.personDAO.ListingPerson();
        return this.personList;
    }

    public void deletePerson() {
        this.personDAO.deletePerson(this.person);
        this.clearInfo();


    }


    public void clearInfo() {
        this.person = new Person();
        this.personList = null;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
