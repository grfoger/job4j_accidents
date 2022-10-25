package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentMem;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.RuleMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem store;
    private final AccidentTypeMem typeStore;
    private final RuleMem ruleStore;

    public AccidentService(AccidentMem store, AccidentTypeMem typeStore, RuleMem ruleStore) {
        this.store = store;
        this.typeStore = typeStore;
        this.ruleStore = ruleStore;
    }

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
