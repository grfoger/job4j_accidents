package ru.job4j.accidents.repositoryjdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class RuleJdbc {

    private final JdbcTemplate jdbc;
    public Collection<Rule> getRules() {
        return jdbc.query("select * from rule",
                (rs, rowNum) -> {
                    return new Rule(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                });
    }

    public Rule getRuleById(int id) {
        return jdbc.queryForObject("select * from rule where id=?",
                (rs, rowNum) -> {
                    return new Rule(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }, id);
    }
}
