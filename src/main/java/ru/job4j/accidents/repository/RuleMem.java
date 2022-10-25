package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem {

    private AtomicInteger countRule = new AtomicInteger(0);

    private final HashMap<Integer, Rule> rules = new HashMap<>();

    public RuleMem() {
        rules.put(countRule.getAndIncrement(), new Rule(0, "Статья 1"));
        rules.put(countRule.getAndIncrement(), new Rule(1, "Статья 2"));
        rules.put(countRule.getAndIncrement(), new Rule(2, "Статья 3"));
    }

    public Collection<Rule> getRules() {
        return rules.values().stream().toList();
    }

    public Rule getRuleById(int id) {
        return rules.get(id);
    }
}
