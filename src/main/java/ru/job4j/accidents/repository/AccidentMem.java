package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {

    private int count = 0;
    private final HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(count++, new Accident(0, "Парковка", "Остановка в неполженном месте", "ул.Герцина"));
    }


    public Collection<Accident> getAll() {
        return accidents.values().stream().toList();
    }

    public void create(Accident accident) {
        accidents.put(count++, accident);
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident getById(int id) {
        return accidents.get(id);
    }
}
