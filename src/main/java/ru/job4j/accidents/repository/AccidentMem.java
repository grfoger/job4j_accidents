package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident(1, "Парковка", "Остановка в неполженном месте", "ул.Герцина"));
    }


    public Collection<Accident> getAll() {
        return accidents.values().stream().toList();
    }

    public void create(Accident accident) {
        accidents.put(accident.getId(), accident);
    }
}
