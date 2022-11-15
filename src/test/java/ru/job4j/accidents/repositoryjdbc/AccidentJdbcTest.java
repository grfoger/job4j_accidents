package ru.job4j.accidents.repositoryjdbc;

import lombok.AllArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.RuleMem;

import javax.sql.DataSource;
import java.util.List;
import java.util.Set;



public class AccidentJdbcTest {

    /**
     * тестовый конфиг тащит через пропертиз
     *
     * если успешно попробовать переписать тест на бины вместо прямого задания параметров
     * затем протестировать методы БД
     * затем дописать все методы БД
     private final JdbcTemplate jdbcTest;
     */
 private final JdbcTemplate jdbcTest = jdbc(
         ds("org.h2.Driver",
                 "jdbc:h2:./testdb;MODE=PostgreSQL;CASE_INSENSITIVE_IDENTIFIERS=TRUE;",
                 "", ""
            ));

    public DataSource ds(String driver,
                          String url,
                          String username,
                          String password) {

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }


    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }


    public void jdbcClear(JdbcTemplate jdbc) {
        jdbc.update("delete from accident_rule");
        jdbc.update("delete from accident");

    }



    @Test
    public void addAndGetTest() {
        AccidentTypeMem typeMem = new AccidentTypeMem();
        RuleMem ruleMem = new RuleMem();
        /**JdbcTemplate templ = jdbc(ds(
                "org.h2.Driver",
                "jdbc:h2:./testdb;MODE=PostgreSQL;CASE_INSENSITIVE_IDENTIFIERS=TRUE;",
                "",
                ""
        ));
         **/
        jdbcClear(jdbcTest);
        AccidentJdbc jdbc = new AccidentJdbc(jdbcTest);

        Accident accident = new Accident(0, typeMem.getTypeById(1),
                Set.of(ruleMem.getRuleById(2), ruleMem.getRuleById(1)),
                "name", "text", "address");
        jdbc.create(accident);
        System.out.println((List) jdbc.getAll());
        /**
         *
         */
    }
}
