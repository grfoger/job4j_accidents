package ru.job4j.accidents.repositoryhbm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class AccidentHibernate {
    public final SessionFactory sf;

    private <T> T tx(final Function<Session, T> command) {
        T result = null;
        try (Session session = sf.openSession()) {
            result = command.apply(session);
        }
        return result;
    }

    public Accident create(Accident accident) {
        tx(session -> session.save(accident));
        return accident;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    public Collection<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }
}
