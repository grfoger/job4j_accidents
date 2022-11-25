package ru.job4j.accidents.repositoryjdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbc {

    private final JdbcTemplate jdbc;

    public Collection<AccidentType> getTypes() {
        return jdbc.query("select * from types",
                (rs, rowNum) -> {
                    return new AccidentType(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                });
    }

    public AccidentType getTypeById(int id) {
        return jdbc.queryForObject("select * from types where id=?",
                (rs, rowNum) -> {
                    return new AccidentType(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }, id);
    }
}
