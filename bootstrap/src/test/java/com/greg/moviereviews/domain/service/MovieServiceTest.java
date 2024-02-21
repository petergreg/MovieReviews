package com.greg.moviereviews.domain.service;

import java.util.Optional;
import lombok.Data;

@Data
class Person {
  private String firstName;
  private String lastName;
}

class MovieServiceTest {

  public Person getPerson_imperative(String firstName, String lastName, String num) {
    if (firstName != null) {
      if (lastName != null) {
        if (num != null) {
          return new Person();
        }
      }
    }
    return null;
  }

  public Optional<Person> getPerson(String firstName, String lastName, String num) {
    return Optional.of(firstName).flatMap(first ->
            Optional.of(lastName).flatMap(last ->
             Optional.of(num).map(n ->
                    new Person())));
  }


  public Optional<Person> getPerson_scala(String firstName, String lastName, String num) {
    return Optional.of(firstName).flatMap(first ->
            Optional.of(lastName).flatMap(last ->
                    Optional.of(num).map(n ->
                            new Person())));
  }

}
