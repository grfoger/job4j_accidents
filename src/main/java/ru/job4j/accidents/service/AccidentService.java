package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.RuleMem;
import ru.job4j.accidents.repositoryhbm.AccidentHibernate;
import ru.job4j.accidents.repositoryhbm.AccidentTypeHibernate;
import ru.job4j.accidents.repositoryhbm.RuleHibernate;
import ru.job4j.accidents.repositoryjdbc.AccidentJdbc;
import ru.job4j.accidents.repositoryjdbc.AccidentTypeJdbc;
import ru.job4j.accidents.repositoryjdbc.RuleJdbc;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentHibernate store;
    private final AccidentTypeHibernate typeStore;
    private final RuleHibernate ruleStore;


    public Collection<Accident> getAll() {
        return store.getAll();
    }

    public void create(Accident accident) {
        store.create(accident);
    }

    public void update(Accident accident) {
        store.update(accident);
    }

    public Accident getById(int id) {
        return store.getById(id);
    }

    public Collection<AccidentType> getTypes() {
        return typeStore.getTypes();
    }

    public AccidentType getTypeById(int id) {
        return typeStore.getTypeById(id);
    }

    public Collection<Rule> getRules() {
        return ruleStore.getRules();
    }

    public Rule getRuleById(int id) {
        return ruleStore.getRuleById(id);
    }
}
