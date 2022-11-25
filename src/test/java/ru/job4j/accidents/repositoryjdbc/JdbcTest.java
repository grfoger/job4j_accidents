package ru.job4j.accidents.repositoryjdbc;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Set;

public class JdbcTest {

    public final JdbcTemplate jdbc;

    public JdbcTest(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Test
    public void createAndGetTest() {
        AccidentTypeJdbc type = new AccidentTypeJdbc(jdbc);
        RuleJdbc rule = new RuleJdbc(jdbc);
        AccidentJdbc accidentJdbc = new AccidentJdbc(jdbc, type, rule);
        Accident accident = new Accident(0, type.getTypeById(1),
                Set.of(rule.getRuleById(2), rule.getRuleById(1)),
                "name", "text", "address");
        accidentJdbc.create(accident);
        System.out.println(((List) accidentJdbc.getAll()).get(1));
    }

    @Test
    public void getRuleTest() {
        RuleJdbc ruleJdbc = new RuleJdbc(jdbc);
        Assert.isTrue("Статья1".equals(ruleJdbc.getRuleById(1).getName()));
    }
}
