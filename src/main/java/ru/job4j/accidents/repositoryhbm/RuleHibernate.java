package ru.job4j.accidents.repositoryhbm;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class RuleHibernate {
    private final SessionFactory sf;

    private <T> T tx(final Function<Session, T> command) {
        T result = null;
        try (Session session = sf.openSession()) {
            result = command.apply(session);
        }
        return result;
    }

    public Collection<Rule> getRules() {
        return tx(session -> session.createQuery("from Rule", Rule.class).list());
    }

    public Rule getRuleById(int id) {
        return tx(session -> (Rule) session.createQuery("from Rule where id = :id")
                .setParameter("id", id).uniqueResult());
    }
}
