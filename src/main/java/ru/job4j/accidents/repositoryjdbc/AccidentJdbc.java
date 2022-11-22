package ru.job4j.accidents.repositoryjdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
@AllArgsConstructor
public class AccidentJdbc {
    private final JdbcTemplate jdbc;
    private final AccidentTypeJdbc typeJdbc;
    private final RuleJdbc ruleJdbc;



    public Collection<Accident> getAll() {
        return jdbc.query("select * from accident",
                (rs, rowNum) -> {
                    return new Accident(
                            rs.getInt("id"),
                            typeJdbc.getTypeById(rs.getInt("type_id")),
                            getRulesById(rs.getInt("id")),
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address")
                    );
                });
    }

    private Set<Rule> getRulesById(int id) {
        Set<Rule> rules = new HashSet<>();
        jdbc.query("select * from accident_rule where accident_id=?",
                (rs, rowNum) -> {
                    return rs.getInt("rule_id");
                }, id)
                .forEach(num -> rules.add(ruleJdbc.getRuleById(num)));
        return rules;
    }


    public Accident create(Accident accident) {
        KeyHolder kh = new GeneratedKeyHolder();
        String request = "insert into accident (type_id, name, text, address)"
                + " VALUES (?, ?, ?, ?)";
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, accident.getType().getId());
            ps.setString(2, accident.getName());
            ps.setString(3, accident.getText());
            ps.setString(4, accident.getAddress());
            return ps;
            }, kh);
        accident.setId((Integer) kh.getKeys().get("id"));
        accident.getRules().forEach(rule -> {
            jdbc.update("insert into accident_rule (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(),
                    rule.getId());
        });
        return accident;
    }

    public Accident getById(int id) {
        return jdbc.queryForObject("select * from accident where id=?",
                (rs, rowNum) -> {
                    return new Accident(
                            rs.getInt("id"),
                            typeJdbc.getTypeById(rs.getInt("type_id")),
                            getRulesById(rs.getInt("id")),
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address")
                    );
                }, id);
    }

    public Accident update(Accident accident) {
        String request = "update accident set"
                + " type_id = ?,"
                + " name = ?,"
                + " text = ?,"
                + " address = ?"
                + " where id = ?";
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, accident.getType().getId());
            ps.setString(2, accident.getName());
            ps.setString(3, accident.getText());
            ps.setString(4, accident.getAddress());
            ps.setInt(5, accident.getId());
            return ps;
        });
        jdbc.update("delete from accident_rule where accident_id = ?",
                accident.getId());
        accident.getRules().forEach(rule -> {
           jdbc.update("insert into accident_rule (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(),
                    rule.getId());
        });
        return accident;
    }
}
