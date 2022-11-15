package ru.job4j.accidents.repositoryjdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;


@Repository
@AllArgsConstructor
public class AccidentJdbc {
    private final JdbcTemplate jdbc;

    public Collection<Accident> getAll() {

        return jdbc.query("select * from accident",
                (rs, rowNum) -> {
                    return new Accident(
                            rs.getInt("id"),
                            null,
                            null,
                            rs.getString("name"),
                            rs.getString("text"),
                            rs.getString("address")
                    );
                });
        /**

         collection.forEach(accident -> {

        });
         **/
    }


    public Accident create(Accident accident) {
        KeyHolder kh = new GeneratedKeyHolder();
        String request = "insert into accident (type_id, name, text, address)"
                + " VALUES (?, ?, ?, ?)";
        int gk;
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, accident.getType().getId());
            ps.setString(2, accident.getName());
            ps.setString(3, accident.getText());
            ps.setString(4, accident.getAddress());
            return ps;
            }, kh);
        accident.setId(kh.getKey().intValue());
        accident.getRules().forEach(rule -> {
            jdbc.update("insert into accident_rule (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(),
                    rule.getId());
        });
        return accident;
    }

    public void update(Accident accident) {

    }

    public Accident getById(int id) {
        return jdbc.queryForObject("select * from accident where id = ?", Accident.class, id);
    }

    /**
    public Accident save(Accident accident) {
        jdbc.update("insert into accident (name) values (?)",
                accident.getName());
        return accident;
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

     */
}
