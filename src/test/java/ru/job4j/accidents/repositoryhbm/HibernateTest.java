package ru.job4j.accidents.repositoryhbm;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.job4j.accidents.model.Accident;

import java.util.Set;

@SpringBootTest
public class HibernateTest {
    @Autowired
    public SessionFactory sf;

    @Test
    public void saveTest() {
        AccidentHibernate accidentHibernate = new AccidentHibernate(sf);
        AccidentTypeHibernate typeHibernate = new AccidentTypeHibernate(sf);
        RuleHibernate ruleHibernate = new RuleHibernate(sf);
        Accident accident = new Accident(0, typeHibernate.getTypeById(2),
                Set.of(ruleHibernate.getRuleById(1), ruleHibernate.getRuleById(3)),
                "name", "text", "address");
                Integer zero = 0;
                Accident after = accidentHibernate.create(accident);

                System.out.println(after);
        Assert.isTrue(!zero.equals(after.getId()));
    }

    @Test
    public void getAll() {
        AccidentHibernate accidentHibernate = new AccidentHibernate(sf);
        System.out.println(accidentHibernate.getAll());
    }
}
