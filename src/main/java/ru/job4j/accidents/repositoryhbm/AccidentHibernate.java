package ru.job4j.accidents.repositoryhbm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    public final SessionFactory sf;

    private <T> T tx(final Function<Session, T> command) {
        T result = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = command.apply(session);
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }

    public Accident create(Accident accident) {
        tx(session -> session.save(accident));
        return accident;
    }

    public Collection<Accident> getAll() {
        return tx(session -> session
                .createQuery("""
                select distinct a from Accident a
                left join fetch a.rules
                join fetch a.type
                """, Accident.class)
                .list());
    }

    public Accident getById(int id) {
        return tx(session -> (Accident) session.createQuery("from Accident where id = :id")
                .setParameter("id", id).uniqueResult());
    }

    public Accident update(Accident accident) {
        tx(session -> {
            session.update(accident);
            return null;
        });
        return accident;
    }


}
