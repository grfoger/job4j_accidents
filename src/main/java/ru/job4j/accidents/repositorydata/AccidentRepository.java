package ru.job4j.accidents.repositorydata;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
}
