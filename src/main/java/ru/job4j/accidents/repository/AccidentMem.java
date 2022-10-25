package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private AtomicInteger count = new AtomicInteger(0);

    private final HashMap<Integer, Accident> accidents = new HashMap<>();


    public AccidentMem(AccidentTypeMem accidentTypes, RuleMem rules) {
        accidents.put(count.getAndIncrement(), new Accident(0, accidentTypes.getTypeById(0), Set.of(rules.getRuleById(0)),
                "Остановка", "Остановка в неполженном месте", "ул.Герцина"));
    }


    public Collection<Accident> getAll() {
        return accidents.values().stream().toList();
    }

    public void create(Accident accident) {
        accident.setId(count.get());
        accidents.put(count.getAndIncrement(), accident);
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident getById(int id) {
        return accidents.get(id);
    }


}
