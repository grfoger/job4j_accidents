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
    private static final String GET_ALL = "SELECT * FROM types";
    private static final String GET_BY_ID = "SELECT * FROM types WHERE id=?";
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
        return jdbc.query(GET_ALL, getRowMapper());
    }

    public AccidentType getTypeById(int id) {
        return jdbc.queryForObject(GET_BY_ID, getRowMapper(), id);
    }
}
