package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class AccidentMem {

    private int count = 0;
    private int countType = 0;
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final HashMap<Integer, AccidentType> accidentsTypes = new HashMap<>();

    public AccidentMem() {
        accidentsTypes.put(countType++, new AccidentType(0, "Парковка"));
        accidentsTypes.put(countType++, new AccidentType(1, "Столкновение"));
        accidentsTypes.put(countType++, new AccidentType(2, "Прочее"));
        accidents.put(count++, new Accident(0, accidentsTypes.get(0), "Остановка", "Остановка в неполженном месте", "ул.Герцина"));
    }


    public Collection<Accident> getAll() {
        return accidents.values().stream().toList();
    }

    public void create(Accident accident) {
        accident.setId(count);
        accidents.put(count++, accident);
    }

    public void update(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Accident getById(int id) {
        return accidents.get(id);
    }

    public Collection<AccidentType> getTypes() {
        return accidentsTypes.values().stream().toList();
    }

    public AccidentType getTypeById(int id) {
       return accidentsTypes.get(id);
    }
}
