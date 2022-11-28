package ru.job4j.accidents.repositoryhbm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate {
    private final SessionFactory sf;

    private <T> T tx(final Function<Session, T> command) {
        T result = null;
        try (Session session = sf.openSession()) {
            result = command.apply(session);
        }
        return result;
    }

    public Collection<AccidentType> getTypes() {
        return tx(session -> session.createQuery("from AccidentType ", AccidentType.class).list());
    }

    public AccidentType getTypeById(int id) {
        return tx(session -> (AccidentType) session.createQuery("from AccidentType where id = :id")
                .setParameter("id", id).uniqueResult());
    }
}
