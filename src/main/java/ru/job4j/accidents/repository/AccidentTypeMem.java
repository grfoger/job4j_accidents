package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {

    private AtomicInteger countType = new AtomicInteger(0);

    private final HashMap<Integer, AccidentType> accidentsTypes = new HashMap<>();

    public AccidentTypeMem() {
        accidentsTypes.put(countType.getAndIncrement(), new AccidentType(0, "Парковка"));
        accidentsTypes.put(countType.getAndIncrement(), new AccidentType(1, "Столкновение"));
        accidentsTypes.put(countType.getAndIncrement(), new AccidentType(2, "Прочее"));
    }

    public Collection<AccidentType> getTypes() {
        return accidentsTypes.values().stream().toList();
    }

    public AccidentType getTypeById(int id) {
        return accidentsTypes.get(id);
    }
}
