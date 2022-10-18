package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public Collection<Accident> getAll() {
        return store.getAll();
    }
}
