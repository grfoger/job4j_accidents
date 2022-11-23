package ru.job4j.accidents.repositoryjdbc;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentTypeMem;
import ru.job4j.accidents.repository.RuleMem;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;


public class JdbcTest {

    public final JdbcTemplate jdbc;

    public JdbcTest() {
        this.jdbc = new JdbcTemplate(ds(
                "org.h2.Driver",
                "jdbc:h2:./testdb;MODE=PostgreSQL;CASE_INSENSITIVE_IDENTIFIERS=TRUE;",
                "",
                ""
        ));
    }


    public DataSource ds(String driver, String url, String username, String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
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
