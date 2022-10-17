package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.HashMap;

@Repository
public class AccidentMem {

    private HashMap<Integer, Accident> accidents = new HashMap<>();

}
