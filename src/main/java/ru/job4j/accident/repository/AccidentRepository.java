package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    @Query("select a from Accident as a join fetch a.rules")
    List<Accident> findAll();

    @Query("select a from Accident as a join fetch a.rules where a.id = ?1")
    Accident findById(int id);
}
