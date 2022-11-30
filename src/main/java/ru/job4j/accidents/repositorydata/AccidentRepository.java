package ru.job4j.accidents.repositorydata;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @EntityGraph(attributePaths = {"type", "rules"}, type = EntityGraph.EntityGraphType.FETCH)
    Collection<Accident> findAll();

    @EntityGraph(attributePaths = {"type", "rules"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Accident> findById(int id);
}
