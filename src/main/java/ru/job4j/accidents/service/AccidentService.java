package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.RuleMem;
import ru.job4j.accidents.repositorydata.AccidentRepository;
import ru.job4j.accidents.repositorydata.AccidentTypeRepository;
import ru.job4j.accidents.repositorydata.RuleRepository;
import ru.job4j.accidents.repositoryhbm.AccidentHibernate;
import ru.job4j.accidents.repositoryhbm.AccidentTypeHibernate;
import ru.job4j.accidents.repositoryhbm.RuleHibernate;
import ru.job4j.accidents.repositoryjdbc.AccidentJdbc;
import ru.job4j.accidents.repositoryjdbc.AccidentTypeJdbc;
import ru.job4j.accidents.repositoryjdbc.RuleJdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class AccidentService {

    private final AccidentRepository store;
    private final AccidentTypeRepository typeStore;
    private final RuleRepository ruleStore;


    public Collection<Accident> getAll() {
        List<Accident> result = new ArrayList<>();
        store.findAll().forEach(result::add);
        return result;
    }

    public void create(Accident accident) {
        store.save(accident);
    }

    public void update(Accident accident) {
        store.save(accident);
    }

    public Accident getById(int id) {
        return store.findById(id).get();
    }

    public Collection<AccidentType> getTypes() {
        List<AccidentType> result = new ArrayList<>();
        typeStore.findAll().forEach(result::add);
        return result;
    }

    public AccidentType getTypeById(int id) {
        return typeStore.findById(id).get();
    }

    public Collection<Rule> getRules() {
        List<Rule> result = new ArrayList<>();
        ruleStore.findAll().forEach(result::add);
        return result;
    }

    public Rule getRuleById(int id) {
        return ruleStore.findById(id).get();
    }
}
