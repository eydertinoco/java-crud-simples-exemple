package br.com.classes.crud.projeto.dao;

import br.com.classes.crud.projeto.connection.ConnectionFactory;
import br.com.classes.crud.projeto.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    public boolean signUpPerson(Person person) {
        Connection connection = new ConnectionFactory().returnConnection();

        String sql = "INSERT INTO person (last_name, first_name, gender, city, email, b_day) VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getGender());
            preparedStatement.setString(4, person.getCity());
            preparedStatement.setString(5, person.getEmail());
            preparedStatement.setDate(6, convertDateUtilInDateSQL(person.getBirthDay()));
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Person> ListingPerson() {
        List<Person> listPerson = new ArrayList<>();
        Connection connection = new ConnectionFactory().returnConnection();

        String sql = "SELECT * FROM person";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Person person = new Person();
                person.setId((resultSet.getInt("person_id")));
                person.setLastName(resultSet.getString("last_name"));
                person.setFirstName(resultSet.getString("first_name"));
                person.setGender(resultSet.getString("gender"));
                person.setCity(resultSet.getString("city"));
                person.setEmail(resultSet.getString("email"));
                person.setBirthDay(resultSet.getDate("b_day"));
                listPerson.add(person);
            }
            return listPerson;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePerson(Person person) {
        Connection connection = new ConnectionFactory().returnConnection();

        String sql = "UPDATE person SET last_name = ?, first_name =?, gender = ?, city = ?, email = ?, b_day = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getGender());
            preparedStatement.setString(4, person.getCity());
            preparedStatement.setString(5, person.getEmail());
            preparedStatement.setDate(6, convertDateUtilInDateSQL(person.getBirthDay()));
            preparedStatement.execute();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean deletePerson(Person person) {
        Connection connection = new ConnectionFactory().returnConnection();
        String sql = "DELETE FROM person WHERE person_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, person.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public java.sql.Date convertDateUtilInDateSQL(java.util.Date date) {
        if (date == null)
            return null;
        else
            return new java.sql.Date(date.getTime());

    }


}

