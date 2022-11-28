package ru.job4j.accidents.repositoryhbm;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.job4j.accidents.model.Accident;

@SpringBootTest
public class HibernateTest {
    @Autowired
    public SessionFactory sf;

    @Test
    public void saveTest() {
        AccidentHibernate accidentHibernate = new AccidentHibernate(sf);
        Accident accident = new Accident(0, null,
                null,
                "name", "text", "address");
                Integer zero = 0;
                Accident after = accidentHibernate.save(accident);
                System.out.println(after);
        Assert.isTrue(!zero.equals(after.getId()));
    }
}
