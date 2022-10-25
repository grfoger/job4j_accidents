package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private AtomicInteger count = new AtomicInteger(0);
    private AtomicInteger countType = new AtomicInteger(0);
    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final HashMap<Integer, AccidentType> accidentsTypes = new HashMap<>();

    public AccidentMem() {
        accidentsTypes.put(countType.getAndIncrement(), new AccidentType(0, "Парковка"));
        accidentsTypes.put(countType.getAndIncrement(), new AccidentType(1, "Столкновение"));
        accidentsTypes.put(countType.getAndIncrement(), new AccidentType(2, "Прочее"));
        accidents.put(count.getAndIncrement(), new Accident(0, accidentsTypes.get(0), "Остановка", "Остановка в неполженном месте", "ул.Герцина"));
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

    public Collection<AccidentType> getTypes() {
        return accidentsTypes.values().stream().toList();
    }

    public AccidentType getTypeById(int id) {
       return accidentsTypes.get(id);
    }
}
