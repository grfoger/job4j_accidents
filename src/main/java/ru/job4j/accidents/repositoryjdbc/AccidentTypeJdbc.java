package ru.job4j.accidents.repositoryjdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbc {
    private static final String GETALL = "SELECT * FROM types";
    private static final String GETBYID = "SELECT * FROM types WHERE id=?";
    private final JdbcTemplate jdbc;

    private RowMapper<AccidentType> getRowMapper() {
        return (rs, rowNum) -> {
            return new AccidentType(
                    rs.getInt("id"),
                    rs.getString("name")
            );
        };
    }

    public Collection<AccidentType> getTypes() {
        return jdbc.query(GETALL, getRowMapper());
    }

    public AccidentType getTypeById(int id) {
        return jdbc.queryForObject(GETBYID, getRowMapper(), id);
    }
}
