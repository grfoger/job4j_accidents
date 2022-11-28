package ru.job4j.accidents.repositoryjdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Repository
@AllArgsConstructor
public class AccidentJdbc {

    private static final String GET_ALL = "SELECT * FROM accident";
    private static final String RULES_BY_ID = "SELECT * FROM accident_rule WHERE accident_id=?";
    private static final String CREATE = """
        INSERT INTO accident (type_id, name, text, address) 
        VALUES (?, ?, ?, ?)
        """;
    private static final String INSERT_RULES = """
        INSERT INTO accident_rule (accident_id, rule_id) 
        VALUES (?, ?)
        """;
    private static final String DELETE_RULES = "DELETE FROM accident_rule WHERE accident_id = ?";
    private static final String GET_BY_ID = "SELECT * FROM accident WHERE id=?";
    private static final String UPDATE = """
            UPDATE accident SET
            type_id = ?,
            name = ?,
            text = ?,
            address = ?
            WHERE id = ?
            """;

    private final JdbcTemplate jdbc;
    private final AccidentTypeJdbc typeJdbc;
    private final RuleJdbc ruleJdbc;

    private RowMapper<Accident> getRowMapper() {
        return (rs, rowNum) -> {
            return new Accident(
                    rs.getInt("id"),
                    typeJdbc.getTypeById(rs.getInt("type_id")),
                    getRulesById(rs.getInt("id")),
                    rs.getString("name"),
                    rs.getString("text"),
                    rs.getString("address")
            );
        };
    }

    public Collection<Accident> getAll() {
        return jdbc.query(GET_ALL, getRowMapper());
    }

    private Set<Rule> getRulesById(int id) {
        Set<Rule> rules = new HashSet<>();
        jdbc.query(RULES_BY_ID,
                (rs, rowNum) -> {
                    return rs.getInt("rule_id");
                }, id)
                .forEach(num -> rules.add(ruleJdbc.getRuleById(num)));
        return rules;
    }


    public Accident create(Accident accident) {
        KeyHolder kh = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, accident.getType().getId());
            ps.setString(2, accident.getName());
            ps.setString(3, accident.getText());
            ps.setString(4, accident.getAddress());
            return ps;
            }, kh);
        accident.setId((Integer) Objects.requireNonNull(kh.getKeys()).get("id"));
        accident.getRules().forEach(rule -> {
            jdbc.update(INSERT_RULES,
                    accident.getId(),
                    rule.getId());
        });
        return accident;
    }

    public Accident getById(int id) {
        return jdbc.queryForObject(GET_BY_ID, getRowMapper(), id);
    }

    public Accident update(Accident accident) {
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, accident.getType().getId());
            ps.setString(2, accident.getName());
            ps.setString(3, accident.getText());
            ps.setString(4, accident.getAddress());
            ps.setInt(5, accident.getId());
            return ps;
        });
        jdbc.update(DELETE_RULES,
                accident.getId());
        accident.getRules().forEach(rule -> {
           jdbc.update(INSERT_RULES,
                    accident.getId(),
                    rule.getId());
        });
        return accident;
    }
}
