package ru.job4j.accidents.repositoryjdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class RuleJdbc {
    private static final String GET_ALL = "SELECT * FROM rule";
    private static final String GET_BY_ID = "SELECT * FROM rule WHERE id=?";
    private final JdbcTemplate jdbc;

    private RowMapper<Rule> getRowMapper() {
        return (rs, rowNum) -> {
            return new Rule(
                    rs.getInt("id"),
                    rs.getString("name")
            );
        };
    }
    public Collection<Rule> getRules() {
        return jdbc.query(GET_ALL, getRowMapper());
    }

    public Rule getRuleById(int id) {
        return jdbc.queryForObject(GET_BY_ID, getRowMapper(), id);
    }
}
